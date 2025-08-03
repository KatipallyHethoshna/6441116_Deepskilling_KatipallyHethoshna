import React from 'react';

class Cart extends React.Component {
    render() {
        const { itemName, price } = this.props;
        return (
            <div>
                <h3>{itemName}</h3>
                <p>Price: ${price}</p>
            </div>
        );
    }
}

export default Cart;
