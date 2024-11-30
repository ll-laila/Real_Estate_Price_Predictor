import { Marker, Popup } from "react-leaflet";
import "./pin.scss";
import { Link } from "react-router-dom";



function Pin({ item }) {
  // Vérification des coordonnées
  const latitude = item.latitude || 0;  // Valeur par défaut si latitude est undefined
  const longitude = item.longitude || 0; // Valeur par défaut si longitude est undefined
  
  if (latitude === 0 && longitude === 0) {
    return null;  // Si les coordonnées sont manquantes, ne pas afficher le marker
  }

  return (
    <Marker position={[latitude, longitude]}>
      <Popup>
        <div className="popupContainer">
          <img src={item.img} alt="" />
          <div className="textContainer">
            <Link to={`/${item.id}`}>{item.title}</Link>
            <span>{item.bedroom} bedroom</span>
            <b>$ {item.price}</b>
          </div>
        </div>
      </Popup>
    </Marker>
  );
}


export default Pin;
