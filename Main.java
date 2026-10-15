import exception.InsufficientStockException;
import exception.ProductNotFoundException;
import model.Electronics;
import model.Product;
import service.InventoryManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        manager.addProduct(new Electronics("E01", "Laptop Dell", 18500000, 5, 12));
        manager.addProduct(new Electronics("E02", "Ban phim co", 1200000, 15, 6));
        
        do {
            System.out.println("\n========= HE THONG QUAN LY KHO HANG ========= ");
            System.out.println("1. Xem danh sach ton kho");
            System.out.println("2. Them san pham dien tu moi");
            System.out.println("3. Tim kiem san pham theo ten");
            System.out.println("4. Xuat kho (Ban hang)");
            System.out.println("0. Thoat chuong trinh");
            System.out.print("Nhap lua chon cua ban: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    manager.displayInventory();
                    break;
                case 2:
                    System.out.print("Nhap ma san pham: ");
                    String id = scanner.nextLine();
                    System.out.print("Nhap ten san pham: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhap gia tien: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    System.out.print("Nhap so luong ton: ");
                    int qty = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nhap thoi gian bao hanh (thang): ");
                    int warranty = Integer.parseInt(scanner.nextLine());

                    manager.addProduct(new Electronics(id, name, price, qty, warranty));
                    break;
                case 3:
                    System.out.print("Nhap tu khoa ten san pham can tim: ");
                    String keyword = scanner.nextLine();
                    List<Product> results = manager.searchByName(keyword);
                    if (results.isEmpty()) {
                        System.out.println("-> Khong tim thay san pham phu hop.");
                    } else {
                        System.out.println("\n--- KET QUA TIM KIEM ---");
                        results.forEach(System.out::println);
                    }
                    break;
                case 4:
                    try {
                        System.out.print("Nhap ma san pham can xuat kho: ");
                        String exId = scanner.nextLine();
                        System.out.print("Nhap so luong xuat: ");
                        int exQty = Integer.parseInt(scanner.nextLine());
                        manager.exportProduct(exId, exQty);
                    } catch (ProductNotFoundException | InsufficientStockException e) {
                        System.out.println("-> Loi giao dich: " + e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("-> Loi: Vui long nhap so hop le.");
                    }
                    break;
                case 0:
                    System.out.println("Cam on ban da su dung he thong!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long chon tu 0 den 4.");
            }
        } while (choice != 0);

        scanner.close();
    }
}