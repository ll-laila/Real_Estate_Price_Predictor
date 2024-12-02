import { Marker } from 'react-leaflet';
import L from 'leaflet';

function Pin({ item }) {
  const { immobilierResponse } = item;
  const { latitude, longitude } = immobilierResponse;

  return (
    <Marker position={[latitude, longitude]} icon={new L.Icon.Default()}>
      {/* You can add custom popups, tooltips, etc., if needed */}
    </Marker>
  );
}

export default Pin;
