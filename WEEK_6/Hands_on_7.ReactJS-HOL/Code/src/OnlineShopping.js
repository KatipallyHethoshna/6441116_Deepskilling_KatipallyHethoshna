import React from 'react';
import Cart from './Cart';

class OnlineShopping extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [
                { itemName: 'Laptop', price: 800 },
                { itemName: 'Smartphone', price: 500 },
                { itemName: 'Headphones', price: 150 },
                { itemName: 'Keyboard', price: 100 },
                { itemName: 'Mouse', price: 50 }
            ]
        };
    }

    render() {
        return (
            <div>
                <h2>Shopping Cart</h2>
                {this.state.items.map((item, index) => (
                    <Cart key={index} itemName={item.itemName} price={item.price} />
                ))}
            </div>
        );
    }
}

export default OnlineShopping;
