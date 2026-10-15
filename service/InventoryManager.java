package service;

import exception.InsufficientStockException;
import exception.ProductNotFoundException;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryManager {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("-> Thêm sản phẩm vào kho thành công!");
    }

    public void displayInventory() {
        if (products.isEmpty()) {
            System.out.println("Kho hàng hiện đang trống.");
            return;
        }
        System.out.println("\n================ DANH SÁCH TỒN KHO ================");
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public List<Product> searchByName(String keyword) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void exportProduct(String id, int amount) throws ProductNotFoundException, InsufficientStockException {
        Product product = products.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Không tìm thấy sản phẩm với mã: " + id));

        if (product.getQuantity() < amount) {
            throw new InsufficientStockException("Số lượng tồn kho không đủ! Hiện tại chỉ còn: " + product.getQuantity());
        }

        product.setQuantity(product.getQuantity() - amount);
        System.out.println("-> Xuất kho thành công " + amount + " sản phẩm [" + product.getName() + "]");
    }
}