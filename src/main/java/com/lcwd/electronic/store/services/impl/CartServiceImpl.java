package com.lcwd.electronic.store.services.impl;

import com.lcwd.electronic.store.dtos.AddItemToCartRequest;
import com.lcwd.electronic.store.dtos.CartDto;
import com.lcwd.electronic.store.entities.Cart;
import com.lcwd.electronic.store.entities.CartItem;
import com.lcwd.electronic.store.entities.Product;
import com.lcwd.electronic.store.entities.User;
import com.lcwd.electronic.store.exceptions.BadApiRequestException;
import com.lcwd.electronic.store.exceptions.ResourceNotFoundException;
import com.lcwd.electronic.store.repositories.CartItemRepository;
import com.lcwd.electronic.store.repositories.CartRepository;
import com.lcwd.electronic.store.repositories.ProductRepository;
import com.lcwd.electronic.store.repositories.UserRepository;
import com.lcwd.electronic.store.services.CartService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {
        int quantity = request.getQuantity();
        String productId = request.getProductId();

        if (quantity <= 0) {
            throw new BadApiRequestException("Requested quantity is not valid !!");
        }

        // Fetch the product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found in database !!"));

        // Fetch or create the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found in database!!"));

        // Fetch or create the cart for the user
        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setCartId(UUID.randomUUID().toString());
            newCart.setCreatedAt(new Date());
            newCart.setUser(user);
            return newCart;
        });

        // Check if the product already exists in the cart
        Optional<CartItem> existingItemOptional = cart.getItems().stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst();

        if (existingItemOptional.isPresent()) {
            // If item exists, update its quantity and total price
            CartItem existingItem = existingItemOptional.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            existingItem.setTotalPrice(existingItem.getQuantity() * product.getDiscountedPrice());
        } else {
            // Otherwise, create a new cart item and add it to the cart
            CartItem cartItem = CartItem.builder()
                    .quantity(quantity)
                    .totalPrice(quantity * product.getDiscountedPrice())
                    .cart(cart)  // Associate with the correct cart
                    .product(product)
                    .build();

            cart.getItems().add(cartItem);
        }

        // Save the updated cart
        Cart updatedCart = cartRepository.save(cart);

        // Map to DTO and return
        return mapper.map(updatedCart, CartDto.class);
    }


//    @Override
//    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {
//        int quantity = request.getQuantity();
//        String productId = request.getProductId();
//
//        if (quantity <= 0) {
//            throw new BadApiRequestException("Requested quantity is not valid !!");
//        }
//
//        // Fetch the product
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found in database !!"));
//
//        // Fetch or create the user
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found in database!!"));
//
//        // Fetch or create the cart for the user
//        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
//            Cart newCart = new Cart();
//            newCart.setCartId(UUID.randomUUID().toString());
//            newCart.setCreatedAt(new Date());
//            newCart.setUser(user);
//            return newCart;
//        });
//
//        // Create new cart item
//        CartItem cartItem = CartItem.builder()
//                .quantity(quantity)
//                .totalPrice(quantity * product.getDiscountedPrice())
//                .cart(cart)  // Associate with the correct cart
//                .product(product)
//                .build();
//
//        // Add the new cart item to the cart
//        cart.getItems().add(cartItem);
//
//        // Save the updated cart
//        Cart updatedCart = cartRepository.save(cart);
//
//        // Map to DTO and return
//        return mapper.map(updatedCart, CartDto.class);
//    }


//    @Override
//    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {
//
//        int quantity = request.getQuantity();
//        String productId = request.getProductId();
//
//        if (quantity <= 0) {
//            throw new BadApiRequestException("Requested quantity is not valid !!");
//        }
//
//        //fetch the product
//        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found in database !!"));
//        //fetch the user from db
//        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found in database!!"));
//
//        Cart cart = null;
//        try {
//            cart = cartRepository.findByUser(user).get();
//        } catch (NoSuchElementException e) {
//            cart = new Cart();
//            cart.setCartId(UUID.randomUUID().toString());
//            cart.setCreatedAt(new Date());
//        }
//
//        //perform cart operations
//        //if cart items already present; then update
//        AtomicReference<Boolean> updated = new AtomicReference<>(false);
//        List<CartItem> items = cart.getItems();
//        items = items.stream().map(item -> {
//
//            if (item.getProduct().getProductId().equals(productId)) {
//                //item already present in cart
//                item.setQuantity(quantity);
//                item.setTotalPrice(quantity * product.getDiscountedPrice());
//                updated.set(true);
//            }
//            return item;
//        }).collect(Collectors.toList());
//
////        cart.setItems(updatedItems);
//
//        //create items
//        if (!updated.get()) {
//            CartItem cartItem = CartItem.builder()
//                    .quantity(quantity)
//                    .totalPrice(quantity * product.getDiscountedPrice())
//                    .cart(cart)
//                    .product(product)
//                    .build();
//            cart.getItems().add(cartItem);
//        }
//
//
//        cart.setUser(user);
//        Cart updatedCart = cartRepository.save(cart);
//        return mapper.map(updatedCart, CartDto.class);
//
//
//    }



//    @Override
//    @Transactional
//    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {
//
//        int quantity = request.getQuantity();
//        String productId = request.getProductId();
//
//        if(quantity<=0){
//            throw new BadApiRequestException("Requested quantity not valid");
//        }
//        //fetch the product
//        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found!!"));
//        //fetch the user
//        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!!"));
//        //fetch the cart
//        Cart cart = null;
//        try{
//            cart = cartRepository.findByUser(user).get();
//        } catch(NoSuchElementException exe){
//            cart = new Cart();
//            cart.setCartId(UUID.randomUUID().toString());
//            cart.setCreatedAt(new Date());
//
//        }
//
//        //perform cart operation
//        //if cart items already present in cart then update
//        AtomicReference<Boolean> updated = new AtomicReference<>(false);
//        List<CartItem> items = cart.getItems();
//        List<CartItem> updatedItems = items.stream().map(item -> {
//
//            if (item.getProduct().getProductId().equals(productId)) {
//                //item already present in cart
//                item.setQuantity(quantity);
//                item.setTotalPrice(quantity * product.getPrice());
//                updated.set(true);
//            }
//            return item;
//        }).collect(Collectors.toList());
//
//        cart.setItems(updatedItems);
//
//
//        //crete items
//       if(!updated.get()){
//           CartItem cartItem = CartItem.builder()
//                   .quantity(quantity)
//                   .totalPrice(quantity * product.getPrice())
//                   .cart(cart)
//                   .product(product)
//                   .build();
//           cart.getItems().add(cartItem);
//       }
//
//        cart.setUser(user);
//
//        Cart updatedcart = cartRepository.save(cart);
//        return mapper.map(updatedcart, CartDto.class);
//    }

//    @Override
//    @Transactional
//    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {
//
//        int quantity = request.getQuantity();
//        String productId = request.getProductId();
//
//        if (quantity <= 0) {
//            throw new BadApiRequestException("Requested quantity not valid");
//        }
//
//        // Fetch the product
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found!!"));
//
//        // Fetch the user
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found!!"));
//
//        // Fetch the cart, create a new one if it doesn't exist
//        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
//            Cart newCart = new Cart();
//            newCart.setCartId(UUID.randomUUID().toString());
//            newCart.setCreatedAt(new Date());
//            newCart.setUser(user);
//            return newCart;
//        });
//
//        // Check if the product is already in the cart
//        CartItem existingItem = cart.getItems().stream()
//                .filter(item -> item.getProduct().getProductId().equals(productId))
//                .findFirst()
//                .orElse(null);
//
//        if (existingItem != null) {
//            // Item already exists in the cart, update it
//            existingItem.setQuantity(quantity);
//            existingItem.setTotalPrice(quantity * product.getDiscountedPrice());
//        } else {
//            // Item not found in the cart, create a new one and add it
//            CartItem cartItem = CartItem.builder()
//                    .quantity(quantity)
//                    .totalPrice(quantity * product.getDiscountedPrice())
//                    .cart(cart)
//                    .product(product)
//                    .build();
//            cart.getItems().add(cartItem);
//        }
//
//        // Save the cart and return the DTO
//        Cart updatedCart = cartRepository.save(cart);
//        return mapper.map(updatedCart, CartDto.class);
//    }


    @Override
    public void removeItemFromCart(String userId, int cartItem) {
        CartItem cartItem1 = cartItemRepository.findById(cartItem).orElseThrow(() -> new ResourceNotFoundException("Not found"));
        cartItemRepository.delete(cartItem1);
    }

    @Override
    public void clearCart(String userId) {


        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!!"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("cart not found"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public CartDto getCartByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!!"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("cart not found"));
        return mapper.map(cart,CartDto.class);
    }


}
