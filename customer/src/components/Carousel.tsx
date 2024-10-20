import React, { useState, useEffect } from 'react';

interface CarouselItem {
  name: string;
  logo: string;
}

interface CarouselProps {
  items: CarouselItem[];
}

const Carousel: React.FC<CarouselProps> = ({ items }) => {
  const [currentIndex, setCurrentIndex] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentIndex((prevIndex) => (prevIndex + 1) % items.length);
    }, 3000); // Change slide every 3 seconds

    return () => clearInterval(interval);
  }, [items.length]);

  return (
    <div className="relative overflow-hidden">
      <div
        className="flex transition-transform duration-500 ease-in-out"
        style={{ transform: `translateX(-${currentIndex * (100 / 3)}%)` }}
      >
        {items.map((item, index) => (
          <div key={index} className="w-1/3 flex-shrink-0 px-2">
            <div className="bg-white p-6 rounded-lg shadow-md text-center">
              <img src={item.logo} alt={item.name} className="w-32 h-32 mx-auto mb-4 rounded-full" />
              <h3 className="text-xl font-semibold">{item.name}</h3>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Carousel;