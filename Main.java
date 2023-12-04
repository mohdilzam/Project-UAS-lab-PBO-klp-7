import java.util.*;

/**
 * Kelas utama yang berisi metode main untuk menjalankan aplikasi belanja online.
 */
public class Main {
    private static Akun akun;
    private static Driver driverAkun;
    private static ListBarang listBarang = new ListBarang();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Metode utama untuk menjalankan aplikasi belanja online.
     *
     * @param args Argumen baris perintah yang diteruskan ke program.
     */
    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n|=========================================|");
            System.out.println("| <++SELAMAT DATANG DI ONLINE SHOPPING++> |");
            System.out.println("|=========================================|");
            System.out.println("\n+----------------------+");
            System.out.println("| Menu:                |");
            System.out.println("| 1. Login as Admin    |");
            System.out.println("| 2. Login as Customer |");
            System.out.println("| 3. Exit              |");
            System.out.println("+----------------------+");

            System.out.print("Masukkan pilihan: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    loginAsAdmin();
                    break;
                case 2:
                    loginAsCustomer();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        }

        System.out.println("Terima kasih! Program selesai.");
        scanner.close(); // Menutup Scanner ketika program selesai
    }

    /**
     * Metode untuk login sebagai Admin.
     */
    private static void loginAsAdmin() {
        System.out.print("Masukkan ID Admin: "); // ID Admin: 000
        String id = scanner.next();
        System.out.print("Masukkan Password Admin: "); // Password Admin: Admin
        String password = scanner.next();

        // Verifikasi ID dan password Admin
        if (id.equals("000") && password.equals("Admin")) {
            System.out.println("Login berhasil!");
            akun = new Admin(id, "Admin");
            driverAkun = new AdminDriver((Admin) akun, listBarang, scanner);

            // Masuk ke menu Admin
            adminMenu();
        } else {
            System.out.println("ID atau Password salah, silahkan coba lagi!");
        }
    }

    /**
     * Metode untuk login sebagai Customer.
     */
    private static void loginAsCustomer() {
        System.out.print("Masukkan ID Customer: "); // ID Customer: 001
        String id = scanner.next();
        System.out.print("Masukkan Password Customer: "); // Password Customer: Customer
        String password = scanner.next();

        // Verifikasi ID dan password Customer
        if (id.equals("001") && password.equals("Customer")) {
            System.out.println("Login berhasil!");
            akun = new Customer(id, "Customer");
            driverAkun = new CustomerDriver((Customer) akun, listBarang, scanner);

            // Masuk ke menu Customer
            userMenu();
        } else {
            System.out.println("ID atau Password salah, silahkan coba lagi!");
        }
    }

    /**
     * Metode untuk menu pengguna (Customer).
     */
    private static void userMenu() {
        boolean userMenuRunning = true;
        while (userMenuRunning) {
            System.out.println("\n+----------------------+");
            System.out.println("| User Menu:           |");
            System.out.println("| 1. Kelola Barang     |");
            System.out.println("| 2. Beli Barang       |");
            System.out.println("| 3. Lihat List Barang |");
            System.out.println("| 4. Checkout          |");
            System.out.println("| 5. Lihat Invoice     |");
            System.out.println("| 0. Logout            |");
            System.out.println("+----------------------+");

            System.out.print("Masukkan pilihan: ");
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    driverAkun.kelolaBarang(scanner);
                    break;
                case 2:
                    driverAkun.beliBarang();
                    break;
                case 3:
                    driverAkun.lihatListBarang();
                    break;
                case 4:
                    driverAkun.checkout();
                    akun = null; // Pindahkan baris ini setelah proses checkout
                    break;
                case 5:
                    ((CustomerDriver) driverAkun).lihatInvoice();
                    break;
                case 0:
                    userMenuRunning = false;
                    akun = null;
                    driverAkun = null;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        }
    }

    /**
     * Metode untuk menu Admin.
     */
    private static void adminMenu() {
        boolean adminMenuRunning = true;
        while (adminMenuRunning) {
            System.out.println("\n+---------------------+");
            System.out.println("| Admin Menu:         |");
            System.out.println("| 1. Kelola Barang    |");
            System.out.println("| 2. Setujui Checkout |");
            System.out.println("| 3. Logout           |");
            System.out.println("+---------------------+");

            System.out.print("Masukkan pilihan: ");
            int adminChoice = scanner.nextInt();

            switch (adminChoice) {
                case 1:
                    driverAkun.kelolaBarang(scanner);
                    break;
                case 2:
                    ((AdminDriver) driverAkun).setujuCheckout();
                    break;
                case 3:
                    adminMenuRunning = false;
                    akun = null;
                    driverAkun = null;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        }
    }
}
