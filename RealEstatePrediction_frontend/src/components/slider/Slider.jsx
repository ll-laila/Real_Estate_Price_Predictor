import { useState } from "react";
import "./slider.scss";

function Slider({ images }) {
  // Initialize imageIndex to null by default
  const [imageIndex, setImageIndex] = useState(null);

  const changeSlide = (direction) => {
    if (!images || images.length === 0) return;

    if (direction === "left") {
      setImageIndex(imageIndex === 0 ? images.length - 1 : imageIndex - 1);
    } else {
      setImageIndex(imageIndex === images.length - 1 ? 0 : imageIndex + 1);
    }
  };

  // If no images are available, display an error message
  if (!images || images.length === 0) {
    return <div>No images available</div>;
  }

  return (
    <div className="slider">
      {/* Only show fullSlider when imageIndex is not null */}
      {imageIndex !== null && (
        <div className="fullSlider">
          <div className="arrow left" onClick={() => changeSlide("left")}>
            <img src="/arrow.png" alt="Left Arrow" />
          </div>
        
          <div className="imgContainer">
            <img src={images[imageIndex]} alt={`Slide ${imageIndex + 1}`} />
          </div>
        
          <div className="arrow right" onClick={() => changeSlide("right")}>
            <img src="/arrow.png" alt="Right Arrow" />
          </div>
        
          <div className="close" onClick={() => setImageIndex(null)}>X</div>
        </div>
      )}

      <div className="bigImage">
        <img 
          src={images[0]} 
          alt="Main image" 
          // Only set imageIndex to 0 when clicked
          onClick={() => setImageIndex(0)} 
        />
      </div>

      <div className="smallImages">
        {images.slice(1).map((image, index) => (
          <img
            src={image}
            alt={`Thumbnail ${index + 1}`}
            key={index}
            // Add 1 to index because we sliced the first image
            onClick={() => setImageIndex(index + 1)}
          />
        ))}
      </div>
    </div>
  );
}

export default Slider;