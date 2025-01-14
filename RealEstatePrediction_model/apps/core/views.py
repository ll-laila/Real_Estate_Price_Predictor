from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status
import pickle
from datetime import datetime
from dateutil.relativedelta import relativedelta
import pandas as pd
from .serializers import HousePricePredictionSerializer

# Charger le modèle SARIMA
MODEL_PATH = 'C:/Users/WIN/Desktop/IA_project/real-estate-prediction-project/apps/core/model/sarima_models_global.pkl'
with open(MODEL_PATH, 'rb') as f:
    sarima_model = pickle.load(f)

@api_view(['POST'])
def house_price_prediction(request):
    serializer = HousePricePredictionSerializer(data=request.data)
    
    if serializer.is_valid():
        current_price = serializer.validated_data['current_price']
        date_to_predict = serializer.validated_data['date_to_predict']
        city = serializer.validated_data['city']
        
        current_date = datetime.now().date()
        if date_to_predict <= current_date:
            return Response(
                {"error": "La date de prédiction doit être dans le futur."},
                status=status.HTTP_400_BAD_REQUEST
            )
        
        months_to_predict = (date_to_predict.year - current_date.year) * 12 + (date_to_predict.month - current_date.month)
        
        if months_to_predict <= 0:
            return Response(
                {"error": "La date de prédiction doit être dans le futur."},
                status=status.HTTP_400_BAD_REQUEST
            )
        
        try:
            if sarima_model.data is None or len(sarima_model.data.endog) == 0:
                return Response(
                    {"error": "Le modèle SARIMA n'a pas de données suffisantes pour effectuer la prédiction."},
                    status=status.HTTP_500_INTERNAL_SERVER_ERROR
                )
            
            future_predictions = sarima_model.get_forecast(steps=months_to_predict)
            prediction_series = future_predictions.predicted_mean
            
            if len(prediction_series) < months_to_predict:
                return Response(
                    {"error": "Le modèle SARIMA ne peut pas prédire aussi loin dans le futur."},
                    status=status.HTTP_500_INTERNAL_SERVER_ERROR
                )
            
            predicted_price = prediction_series.iloc[-1]
            predicted_price_adjusted = current_price * (predicted_price / sarima_model.data.endog[-1])

            return Response(
                {
                    "city": city,
                    "date_to_predict": date_to_predict,
                    "predicted_price": round(predicted_price_adjusted, 2)
                },
                status=status.HTTP_200_OK
            )
        except Exception as e:
            return Response(
                {"error": f"Erreur lors de la prédiction: {str(e)}"},
                status=status.HTTP_500_INTERNAL_SERVER_ERROR
            )
    
    return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
