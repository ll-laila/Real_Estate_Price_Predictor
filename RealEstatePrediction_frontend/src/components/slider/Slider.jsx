import { useState } from "react";
import "./slider.scss";

function Slider({ images }) {
  const [imageIndex, setImageIndex] = useState(null);

  const changeSlide = (direction) => {
    if (direction === "left") {
      setImageIndex((prevIndex) => 
        prevIndex === 0 ? images.length - 1 : prevIndex - 1
      );
    } else {
      setImageIndex((prevIndex) => 
        prevIndex === images.length - 1 ? 0 : prevIndex + 1
      );
    }
  };

  const handleKeyDown = (e) => {
    if (imageIndex !== null) {
      switch (e.key) {
        case 'ArrowLeft':
          changeSlide('left');
          break;
        case 'ArrowRight':
          changeSlide('right');
          break;
        case 'Escape':
          setImageIndex(null);
          break;
        default:
          break;
      }
    }
  };

  return (
    <div className="slider" onKeyDown={handleKeyDown} tabIndex={0}>
      {imageIndex !== null && (
        <div className="fullSlider">
          <div 
            className="arrow left" 
            onClick={() => changeSlide("left")}
            aria-label="Previous image"
          >
            <img src="/arrow.png" alt="Previous" />
          </div>
          <div className="imgContainer">
            <img 
              src={images[imageIndex]} 
              alt={`Slide ${imageIndex + 1}`} 
            />
          </div>
          <div 
            className="arrow right" 
            onClick={() => changeSlide("right")}
            aria-label="Next image"
          >
            <img src="/arrow.png" alt="Next" />
          </div>
          <button 
            className="close" 
            onClick={() => setImageIndex(null)}
            aria-label="Close slider"
          >
            ✕
          </button>
        </div>
      )}
      <div className="bigImage">
        <img 
          src={images[0]} 
          alt="Main image" 
          onClick={() => setImageIndex(0)} 
        />
      </div>
      <div className="smallImages">
        {images.slice(1).map((image, index) => (
          <img
            src={image}
            alt={`Thumbnail ${index + 2}`}
            key={index}
            onClick={() => setImageIndex(index + 1)}
          />
        ))}
      </div>
    </div>
  );
}

export default Slider;