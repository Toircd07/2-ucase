
package toi.run;

import toi.dao.NhanVienDAO;
import toi.model.NhanVien;

import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        NhanVienDAO dao = new NhanVienDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== QUAN LY NHAN VIEN =====");
            System.out.println("1. Them nhan vien");
            System.out.println("2. Hien thi danh sach");
            System.out.println("3. Cap nhat nhan vien");
            System.out.println("4. Xoa nhan vien");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

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
                    System.out.println("Thoat!");
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }

    // ===== THEM =====
    public static void themNhanVien(Scanner sc, NhanVienDAO dao) {
        System.out.print("Ten: ");
        String name = sc.nextLine();
        System.out.print("Nam sinh: ");
        int year = sc.nextInt();
        sc.nextLine();
        System.out.print("SDT: ");
        String sdt = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Chuc vu: ");
        String chucvu = sc.nextLine();
        NhanVien nv = new NhanVien(0, name, year, sdt, email, chucvu);
        dao.addNhanVien(nv);
    }

   // ===== HIEN THI =====
public static void hienThi(NhanVienDAO dao) {
    List<NhanVien> list = dao.getAll();
        System.out.println("                      =======================");
        System.out.println("                      | DANH SACH NHAN VIEN |");
        System.out.println("                      =======================");
    // Tính độ dài tối đa của từng cột
    int maxIdLength = "ID".length();
    int maxNameLength = "Name".length();
    int maxYearLength = "DoB".length();
    int maxSdtLength = "SDT".length();
    int maxEmailLength = "Email".length();
    int maxChucVuLength = "Chuc vu".length();

    for (NhanVien nv : list) {
        maxIdLength = Math.max(maxIdLength, String.valueOf(nv.id).length());
        maxNameLength = Math.max(maxNameLength, nv.name.length());
        maxYearLength = Math.max(maxYearLength, String.valueOf(nv.year).length());
        maxSdtLength = Math.max(maxSdtLength, nv.sdt.length());
        maxEmailLength = Math.max(maxEmailLength, nv.email.length());
        maxChucVuLength = Math.max(maxChucVuLength, nv.chucvu.length());
    }

    // Định dạng tiêu đề và nội dung
    String format = "| %-" + maxIdLength + "s | %-" + maxNameLength + "s | %-" + maxYearLength + "s | %-" +
                    maxSdtLength + "s | %-" + maxEmailLength + "s | %-" + maxChucVuLength + "s |";
    String separator = String.format(format, "-".repeat(maxIdLength), "-".repeat(maxNameLength),
            "-".repeat(maxYearLength), "-".repeat(maxSdtLength), "-".repeat(maxEmailLength),
            "-".repeat(maxChucVuLength)).replace(' ', '-');

    // In bảng
    System.out.println(separator);
    System.out.println(String.format(format, "ID", "Name", "DoB", "SDT", "Email", "Chuc vu"));
    System.out.println(separator);

    for (NhanVien nv : list) {
        System.out.println(String.format(format, nv.id, nv.name, nv.year, nv.sdt, nv.email, nv.chucvu));
    }
    System.out.println(separator);
}

    // ===== UPDATE =====
    public static void capNhat(Scanner sc, NhanVienDAO dao) {
        System.out.print("Nhap ID can sua: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Ten moi: ");
        String name = sc.nextLine();

        System.out.print("Nam sinh moi: ");
        int year = sc.nextInt();
        sc.nextLine();

        System.out.print("SDT moi: ");
        String sdt = sc.nextLine();

        System.out.print("Email moi: ");
        String email = sc.nextLine();

        System.out.print("Chuc vu moi: ");
        String chucvu = sc.nextLine();

        NhanVien nv = new NhanVien(id, name, year, sdt, email, chucvu);
        dao.updateNhanVien(nv);
    }

    // ===== DELETE =====
    public static void xoa(Scanner sc, NhanVienDAO dao) {
        System.out.print("Nhap ID can xoa: ");
        int id = sc.nextInt();

        dao.deleteNhanVien(id);
    }
}