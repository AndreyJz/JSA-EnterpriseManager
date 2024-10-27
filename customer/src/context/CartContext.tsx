import React, { createContext, useContext, useState, ReactNode } from 'react';

interface CartItem {
		"id": {
			"branchId": number,
			"serviceId": number
		},
		"service": {
			"id": number,
			"name": string,
			"requiresSupply": boolean
		},
		"branch": {
			"id": number,
			"name": string,
			"nit": string,
			"creationDate": string,
			"city": {
				"id": number,
				"name": string,
				"region": {
					"id": number,
					"name": string,
					"country": {
						"id": number,
						"name": string
					}
				}
			},
			"company": {
				"id": number,
				"name": string,
				"companyType": {
					"id": number,
					"description": string
				}
			}
		},
		"serviceValue":number
	
  quantity: number;
}

interface CartContextType {
  cart: CartItem[];
  addToCart: (item: Omit<CartItem, 'quantity'>) => void;
  removeFromCart: (id: number) => void;
  clearCart: () => void;
  updateQuantity: (id: number, quantity: number) => void;
}

const CartContext = createContext<CartContextType | undefined>(undefined);

export function CartProvider({ children }: { children: ReactNode }) {
  const [cart, setCart] = useState<CartItem[]>([]);

  const addToCart = (item: Omit<CartItem, 'quantity'>) => {
    setCart(prevCart => {
      const existingItem = prevCart.find(cartItem => cartItem.service.id === item.service.id);
      if (existingItem) {
        return prevCart.map(cartItem =>
          cartItem.service.id === item.service.id
            ? { ...cartItem, quantity: cartItem.quantity + 1 }
            : cartItem
        );
      }
      return [...prevCart, { ...item, quantity: 1 }];
    });
  };

  const removeFromCart = (id: number) => {
    setCart(prevCart => prevCart.filter(item => item.service.id !== id));
  };

  const clearCart = () => {
    setCart([]);
  };

  const updateQuantity = (id: number, quantity: number) => {
    setCart(prevCart =>
      prevCart.map(item =>
        item.service.id === id ? { ...item, quantity: Math.max(0, quantity) } : item
      ).filter(item => item.quantity > 0)
    );
  };

  return (
    <CartContext.Provider value={{ cart, addToCart, removeFromCart, clearCart, updateQuantity }}>
      {children}
    </CartContext.Provider>
  );
}

export function useCart() {
  const context = useContext(CartContext);
  if (context === undefined) {
    throw new Error('useCart must be used within a CartProvider');
  }
  return context;
}