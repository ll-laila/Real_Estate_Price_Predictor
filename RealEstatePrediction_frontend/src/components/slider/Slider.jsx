import { useState } from "react";
import "./slider.scss";

function Slider({ images }) {
  // Assurer que l'index de l'image est initialisé à 0 si le tableau 'images' n'est pas vide
  const [imageIndex, setImageIndex] = useState(images && images.length > 0 ? 0 : null);

  const changeSlide = (direction) => {
    if (!images || images.length === 0) return; // Vérifier si le tableau 'images' est vide

    if (direction === "left") {
      setImageIndex(imageIndex === 0 ? images.length - 1 : imageIndex - 1);
    } else {
      setImageIndex(imageIndex === images.length - 1 ? 0 : imageIndex + 1);
    }
  };

  // Si aucune image n'est disponible, afficher un message d'erreur ou un message d'absence d'image
  if (!images || images.length === 0) {
    return <div>No images available</div>;
  }

  return (
    <div className="slider">
    {/*}
      {imageIndex !== null && (
        <div className="fullSlider">
          <div className="arrow" onClick={() => changeSlide("left")}>
            <img src="/arrow.png" alt="Left arrow" />
          </div>
          <div className="imgContainer">
            <img src={images[imageIndex]} alt={`Slide ${imageIndex}`} />
          </div>
          <div className="arrow" onClick={() => changeSlide("right")}>
            <img src="/arrow.png" className="right" alt="Right arrow" />
          </div>
          <div className="close" onClick={() => setImageIndex(null)}>
            X
          </div>
        </div>
      )}
      */}

      <div className="bigImage">
        <img src={images[0]} alt="Main image" onClick={() => setImageIndex(0)} />
      </div>

      <div className="smallImages">
        {images.slice(1).map((image, index) => (
          <img
            src={image}
            alt={`Thumbnail ${index + 1}`}
            key={index}
            onClick={() => setImageIndex(index + 1)}
          />
        ))}
      </div>
    </div>
  );
}

export default Slider;
