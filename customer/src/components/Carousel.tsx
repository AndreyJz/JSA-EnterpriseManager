import React from 'react';
import Company from './Company';
import './Carousel.css';

interface CarouselItem {
  id: number;
  name: string;
  companyType: {
    id: number;
    description: string;
  };
}

interface CarouselProps {
  items: CarouselItem[];
}

const Carousel: React.FC<CarouselProps> = ({ items }) => {
  // se duplican las listas para que no queden espacios en blanco
  const duplicatedItems = [...items, ...items, ...items, ...items];

  return (
    <div className="relative overflow-hidden">
      <div className="carousel">
        <div className="group">
          {duplicatedItems.map((item, index) => (
            <Company key={index} companyData={item} />
          ))}
        </div>
      </div>
    </div>
  );
};

export default Carousel;
