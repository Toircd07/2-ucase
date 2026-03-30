package toi.run;

import toi.dao.*;
import toi.model.*;

import java.util.Scanner;

public class Main {
   
    private static User loggedUser = null;  // lưu người dùng đã đăng nhập
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("ĐANG CHẠY MAIN MỚI");
        while (true) {
            if (loggedUser == null) {
                showLoginMenu();
            } else {
                // Sau khi đăng nhập, hiển thị menu theo quyền
                if (loggedUser.getType().equals("admin")) {
                    showAdminMenu();
                } else {
                    showCustomerMenu();
                }
            }
        }
    }

    // ========== MENU ĐĂNG NHẬP ==========
    private static void showLoginMenu() {
        System.out.println("\n===== ĐĂNG NHẬP HỆ THỐNG =====");
        System.out.print("Tên đăng nhập: ");
        String username = sc.nextLine();
        System.out.print("Mật khẩu: ");
        String password = sc.nextLine();

        UserDAO userDAO = new UserDAO();
        User user = userDAO.login(username, password);
        if (user != null) {
            loggedUser = user;
            System.out.println("Đăng nhập thành công! Chào " + user.getFullName());
        } else {
            System.out.println("Sai tên đăng nhập hoặc mật khẩu!");
        }
    }

    // ========== MENU DÀNH CHO ADMIN ==========
    private static void showAdminMenu() {
        while (true) {
            System.out.println("\n===== MENU QUẢN TRỊ =====");
            System.out.println("1. Quản lý nhân viên (CRUD)");
            System.out.println("2. Quản lý phòng");
            System.out.println("3. Quản lý dịch vụ");
            System.out.println("4. Xem tất cả đặt phòng/dịch vụ");
            System.out.println("5. Xem tất cả hóa đơn");
            System.out.println("0. Đăng xuất");
            System.out.print("Chọn: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    manageEmployee(); // gọi lại chức năng quản lý nhân viên cũ
                    break;
                case 2:
                    System.out.println("Chức năng quản lý phòng đang phát triển.");
                    break;
                case 3:
                    System.out.println("Chức năng quản lý dịch vụ đang phát triển.");
                    break;
                case 4:
                    System.out.println("Xem tất cả đặt phòng/dịch vụ đang phát triển.");
                    break;
                case 5:
                    System.out.println("Xem tất cả hóa đơn đang phát triển.");
                    break;
                case 0:
                    loggedUser = null;
                    System.out.println("Đã đăng xuất.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
            }
        }
    }

    // ========== MENU DÀNH CHO KHÁCH HÀNG ==========
    private static void showCustomerMenu() {
        while (true) {
            System.out.println("\n===== MENU KHÁCH HÀNG =====");
            System.out.println("1. Xem danh sách phòng trống");
            System.out.println("2. Đặt phòng (CRUD)");
            System.out.println("3. Xem danh sách dịch vụ");
            System.out.println("4. Đặt dịch vụ (thêm/xóa)");
            System.out.println("5. Xem hóa đơn của tôi");
            System.out.println("0. Đăng xuất");
            System.out.print("Chọn: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    System.out.println("Chức năng xem phòng trống đang phát triển.");
                    break;
                case 2:
                    manageBooking(); // tạm thời in thông báo
                    break;
                case 3:
                    System.out.println("Chức năng xem dịch vụ đang phát triển.");
                    break;
                case 4:
                    manageServiceBooking();
                    break;
                case 5:
                    viewMyInvoices();
                    break;
                case 0:
                    loggedUser = null;
                    System.out.println("Đã đăng xuất.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
            }
        }
    }

    // ========== CÁC HÀM GIẢ LẬP CHO KHÁCH HÀNG ==========
    private static void manageBooking() {
        System.out.println("Chức năng đặt phòng (CRUD) đang phát triển.");
        // Sau này sẽ gọi BookingDAO với điều kiện user_id = loggedUser.getId()
    }

    private static void manageServiceBooking() {
        System.out.println("Chức năng đặt dịch vụ (thêm/xóa) đang phát triển.");
        // Sau này sẽ gọi ServiceDAO và BookingDAO
    }

    private static void viewMyInvoices() {
        System.out.println("Chức năng xem hóa đơn của tôi đang phát triển.");
        // Sau này sẽ gọi InvoiceDAO với điều kiện user_id = loggedUser.getId()
    }

    // ========== GIỮ LẠI CHỨC NĂNG QUẢN LÝ NHÂN VIÊN CŨ ==========
    private static void manageEmployee() {
        NhanVienDAO dao = new NhanVienDAO();
        while (true) {
            System.out.println("\n--- QUẢN LÝ NHÂN VIÊN ---");
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Hiển thị danh sách");
            System.out.println("3. Cập nhật nhân viên");
            System.out.println("4. Xóa nhân viên");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    themNhanVien(sc, dao);
                    break;
                case 2:
                    hienThi(dao);
                    break;
                case 3:
                    capNhat(sc, dao);
                    break;
                case 4:
                    xoa(sc, dao);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
            }
        }
    }

    // Các hàm hỗ trợ quản lý nhân viên (giữ nguyên từ code cũ)
    private static void themNhanVien(Scanner sc, NhanVienDAO dao) {
        System.out.print("Tên: ");
        String name = sc.nextLine();
        System.out.print("Năm sinh: ");
        int year = getIntInput();
        System.out.print("SDT: ");
        String sdt = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Chức vụ: ");
        String chucvu = sc.nextLine();
        NhanVien nv = new NhanVien(0, name, year, sdt, email, chucvu);
        dao.addNhanVien(nv);
    }

    private static void hienThi(NhanVienDAO dao) {
        var list = dao.getAll();
        if (list.isEmpty()) {
            System.out.println("Danh sách nhân viên trống.");
        } else {
            for (NhanVien nv : list) {
                System.out.println(nv.id + " | " + nv.name + " | " + nv.year + " | " + nv.sdt + " | " + nv.email + " | " + nv.chucvu);
            }
        }
    }

    private static void capNhat(Scanner sc, NhanVienDAO dao) {
        System.out.print("ID cần sửa: ");
        int id = getIntInput();
        System.out.print("Tên mới: ");
        String name = sc.nextLine();
        System.out.print("Năm sinh mới: ");
        int year = getIntInput();
        System.out.print("SDT mới: ");
        String sdt = sc.nextLine();
        System.out.print("Email mới: ");
        String email = sc.nextLine();
        System.out.print("Chức vụ mới: ");
        String chucvu = sc.nextLine();
        NhanVien nv = new NhanVien(id, name, year, sdt, email, chucvu);
        dao.updateNhanVien(nv);
    }

    private static void xoa(Scanner sc, NhanVienDAO dao) {
        System.out.print("ID cần xóa: ");
        int id = getIntInput();
        dao.deleteNhanVien(id);
    }

    // Hàm hỗ trợ nhập số nguyên an toàn
    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Vui lòng nhập số hợp lệ: ");
            }
        }
    }
}