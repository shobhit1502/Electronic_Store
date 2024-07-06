package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.AddItemToCartRequest;
import com.lcwd.electronic.store.dtos.CartDto;

public interface CartService {

    //add Item
    CartDto addItemToCart(String userId, AddItemToCartRequest request);

    //remove Item from Cart
    void removeItemFromCart(String userId,int cartItem);

    //clear cart
    void clearCart(String userId);

    CartDto getCartByUser(String userId);
}
