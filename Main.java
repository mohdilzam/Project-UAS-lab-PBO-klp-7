import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Existing classes
class Barang {
    private String id;
    private String nama;
    private double harga;
    private int jumlah; // Added field for quantity

    public Barang(String id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.jumlah = 0; // Default quantity is set to 0
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getJumlah() {
        return jumlah;
    }

    @Override
    public String toString() {
        return "Barang{" +
                "id='" + id + '\'' +
                ", nama='" + nama + '\'' +
                ", harga=" + harga +
                ", jumlah=" + jumlah +
                '}';
    }
}


class ListBarang {
    private List<Barang> listBarang;

    public ListBarang() {
        this.listBarang = new ArrayList<>();
    }

    public void tambahBarang(Barang barang) {
        listBarang.add(barang);
    }

    public List<Barang> getBarang() {
        return listBarang;
    }

    public Barang hapusBarang(String id) {
        for (Barang barang : listBarang) {
            if (barang.getId().equals(id)) {
                listBarang.remove(barang);
                return barang;
            }
        }
        return null;
    }

    public Barang cariBarangById(String id) {
        for (Barang barang : listBarang) {
            if (barang.getId().equals(id)) {
                return barang;
            }
        }
        return null;
    }

    public void checkout() {
        // Implementation for checkout logic
    }
    public void clear() {
        listBarang.clear();
    }
}

class Transaksi {
    private Akun akun;
    private List<Barang> barang;

    public Transaksi(Akun akun) {
        this.akun = akun;
        this.barang = new ArrayList<>();
    }

    public void setBarang(List<Barang> barang) {
        this.barang = barang;
    }

    public List<Barang> getBarang() {
        return barang;
    }

    @Override
    public String toString() {
        return "Transaksi{" + "akun=" + akun.getId() + ", barang=" + barang + '}';
    }
}
class Invoice {
    private Transaksi transaksi;
    private Pembayaran pembayaran;

    public Invoice(Transaksi transaksi, Pembayaran pembayaran) {
        this.transaksi = transaksi;
        this.pembayaran = pembayaran;
    }

    public void cetakInvoice() {
        System.out.println("======= INVOICE =======");
        System.out.println("Transaksi ID: " + transaksi.hashCode());
        System.out.println("Pembayaran: " + pembayaran.getId());
        System.out.println("Barang yang dibeli:");
        for (Barang barang : transaksi.getBarang()) {
            System.out.println("- " + barang.getNama() + " - Rp " + barang.getHarga());
        }
        System.out.println("=======================");
    }
}
interface Pembayaran {
    String getId();
}
class QRIS implements Pembayaran {
    private String id;

    public QRIS(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
class Bank implements Pembayaran {
    private String id;

    public Bank(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
class COD implements Pembayaran {
    private String id;

    public COD(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}

abstract class Akun {
    private String id;
    private String nama;

    public Akun(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public abstract void setPembayaran(Pembayaran pembayaran);

    public void login() {
        // Implementasi login
        System.out.println("Login as: " + getNama());
    }

    public ListBarang getBasket() {
        // Return the basket for the user (Customer)
        return null;
    }
}

class Customer extends Akun {
    private ListBarang basket;

    public Customer(String id, String nama) {
        super(id, nama);
        this.basket = new ListBarang();
    }

    public ListBarang getBasket() {
        return basket;
    }

    @Override
    public void setPembayaran(Pembayaran pembayaran) {
        // Implementasi untuk Customer
    }
}

class Admin extends Akun {
    public Admin(String id, String nama) {
        super(id, nama);
    }

    @Override
    public void setPembayaran(Pembayaran pembayaran) {
        // Implementasi untuk Admin
    }
}

// New classes

class Keranjang {
    private List<Barang> barang;

    public Keranjang() {
        this.barang = new ArrayList<>();
    }

    public void tambahBarang(Barang barang) {
        this.barang.add(barang);
    }

    public List<Barang> getBarang() {
        return barang;
    }
}

abstract class Driver {
    protected Akun akun;
    protected ListBarang listBarang;

    public Driver(Akun akun, ListBarang listBarang) {
        this.akun = akun;
        this.listBarang = listBarang;
    }

    public abstract void login();

    public abstract void kelolaBarang(Scanner scanner);

    public abstract void beliBarang();

    public abstract void lihatListBarang();

    public abstract void checkout();

    public abstract void pilihMetodePembayaran();
}


class AdminDriver extends Driver {
    public AdminDriver(Akun akun, ListBarang listBarang) {
        super(akun, listBarang);
    }

    @Override
    public void login() {
        System.out.println("Admin " + akun.getId() + " login.");
    }

    @Override
    public void kelolaBarang(Scanner scanner) {
        try {
            while (true) {
                System.out.println("\nList Barang:");
                for (Barang barang : listBarang.getBarang()) {
                    System.out.println(barang);
                }
                System.out.println("\nMenu Kelola Barang:");
                System.out.println("1. Tambah Barang");
                System.out.println("2. Hapus Barang");
                System.out.println("3. Edit Barang");
                System.out.println("0. Kembali");
                System.out.print("Pilih opsi: ");
                int pilihan = scanner.nextInt();
                switch (pilihan) {
                    case 1:
                        System.out.print("Masukkan ID barang: ");
                        String idBarang = scanner.next();
                        System.out.print("Masukkan Nama barang: ");
                        String namaBarang = scanner.next();
                        double hargaBarang = 0.0;
                        while (true) {
                            try {
                                System.out.print("Masukkan Harga barang: ");
                                hargaBarang = scanner.nextDouble();
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Harga tidak valid. Masukkan angka.");
                                scanner.next();
                            }
                        }
                        Barang barang = new Barang(idBarang, namaBarang, hargaBarang);
                        listBarang.tambahBarang(barang);
                        System.out.println("Barang berhasil ditambahkan: " + barang);
                        break;
                    case 2:
                        System.out.print("Masukkan ID barang yang akan dihapus: ");
                        String idBarangHapus = scanner.next();
                        Barang barangHapus = listBarang.hapusBarang(idBarangHapus);
                        if (barangHapus != null) {
                            System.out.println("Barang berhasil dihapus: " + barangHapus);
                        } else {
                            System.out.println("Barang dengan ID " + idBarangHapus + " tidak ditemukan.");
                        }
                        break;
                    case 3:
                        System.out.print("Masukkan ID barang yang akan diedit: ");
                        String idBarangEdit = scanner.next();
                        Barang barangEdit = listBarang.cariBarangById(idBarangEdit);
                        if (barangEdit != null) {
                            System.out.print("Masukkan Nama barang baru: ");
                            String namaBarangEdit = scanner.next();
                            System.out.print("Masukkan Harga barang baru: ");
                            double hargaBarangEdit = scanner.nextDouble();
                            barangEdit.setNama(namaBarangEdit);
                            barangEdit.setHarga(hargaBarangEdit);
                            System.out.println("Barang berhasil diedit: " + barangEdit);
                        } else {
                            System.out.println("Barang dengan ID " + idBarangEdit + " tidak ditemukan.");
                        }
                        break;
                    case 0:
                        return; // Keluar dari metode jika pilihan adalah 0
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                        break;
                }
            }
        }catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Silakan coba lagi.");
            scanner.nextLine(); // Consume the invalid input
        }
    }

    @Override
    public void beliBarang() {
        // Admin cannot buy items
        System.out.println("Admin tidak dapat melakukan pembelian.");
    }

    @Override
    public void lihatListBarang() {
        // Admin can view the list of items
        System.out.println("\nList Barang:");
        for (Barang barang : listBarang.getBarang()) {
            System.out.println(barang);
        }
    }

    @Override
    public void checkout() {
        // Admin cannot checkout
        System.out.println("Admin tidak dapat melakukan checkout.");
    }

    @Override
    public void pilihMetodePembayaran() {
        // Admin cannot choose a payment method
        System.out.println("Admin tidak dapat memilih metode pembayaran.");
    }
}
class CustomerDriver extends Driver {
    private Scanner scanner;
    public CustomerDriver(Akun akun, ListBarang listBarang, Scanner scanner) {
        super(akun, listBarang);
        this.scanner = scanner;

    }

    @Override
    public void login() {
        System.out.println("Customer " + akun.getId() + " login.");
    }

    @Override
    public void kelolaBarang(Scanner scanner) {
        try {
            while (true) {
                System.out.println("\nList Barang:");
                for (Barang barang : listBarang.getBarang()) {
                    System.out.println(barang);
                }
                System.out.println("\nMenu Kelola Barang:");
                System.out.println("1. Tambah Barang");
                System.out.println("2. Hapus Barang");
                System.out.println("3. Edit Barang");
                System.out.println("0. Kembali");
                System.out.print("Pilih opsi: ");
                int pilihan = scanner.nextInt();
                switch (pilihan) {
                    case 1:
                        System.out.print("Masukkan ID barang yang akan ditambahkan ke keranjang: ");
                        String idBarangTambah = scanner.next();
                        Barang barangTambah = listBarang.cariBarangById(idBarangTambah);
                        if (barangTambah != null) {
                            akun.getBasket().tambahBarang(barangTambah);
                            System.out.println("Barang berhasil ditambahkan ke keranjang.");
                        } else {
                            System.out.println("Barang dengan ID " + idBarangTambah + " tidak ditemukan.");
                        }
                        break;
                    case 2:
                        System.out.print("Masukkan ID barang yang akan dihapus dari keranjang: ");
                        String idBarangHapus = scanner.next();
                        Barang barangHapus = akun.getBasket().hapusBarang(idBarangHapus);
                        if (barangHapus != null) {
                            System.out.println("Barang berhasil dihapus dari keranjang: " + barangHapus);
                        } else {
                            System.out.println("Barang dengan ID " + idBarangHapus + " tidak ditemukan dalam keranjang.");
                        }
                        break;
                    case 3:
                        System.out.print("Masukkan ID barang yang akan diubah jumlahnya dalam keranjang: ");
                        String idBarangEdit = scanner.next();
                        Barang barangEdit = akun.getBasket().cariBarangById(idBarangEdit);
                        if (barangEdit != null) {
                            System.out.print("Masukkan jumlah barang baru: ");
                            int jumlahBarangEdit = scanner.nextInt();
                            barangEdit.setJumlah(jumlahBarangEdit);
                            System.out.println("Jumlah barang dalam keranjang berhasil diubah: " + barangEdit);
                        } else {
                            System.out.println("Barang dengan ID " + idBarangEdit + " tidak ditemukan dalam keranjang.");
                        }
                        break;
                    case 0:
                        return; // Keluar dari metode jika pilihan adalah 0
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Silakan coba lagi.");
            scanner.nextLine(); // Consume the invalid input
        }
    }
       @Override
        public void beliBarang() {
        // Add the provided code here
        if (listBarang == null) {
            System.out.println("Error: listBarang is null");
            return;
        }

        if (akun == null) {
            System.out.println("Error: akun is null");
            return;
        }

        System.out.print("Masukkan ID barang yang akan dibeli: ");
        String idBarangBeli = scanner.next();
        Barang barangBeli = listBarang.cariBarangById(idBarangBeli);

        if (barangBeli != null) {
            ListBarang basket = akun.getBasket();
            if (basket != null) {
                basket.tambahBarang(barangBeli);
                System.out.println("Barang berhasil ditambahkan ke keranjang.");
            } else {
                System.out.println("Error: Basket is null");
            }
        } else {
            System.out.println("Barang dengan ID " + idBarangBeli + " tidak ditemukan.");
        }
    }


    @Override
    public void lihatListBarang() {
        // Customer can view the list of items
        System.out.println("\nList Barang:");
        for (Barang barang : listBarang.getBarang()) {
            System.out.println(barang);
        }
    }

        @Override
        public void checkout() {
            // Customer can checkout
            try {
                while (true) {
                    System.out.println("\nKeranjang Belanja:");
                    for (Barang barang : akun.getBasket().getBarang()) {
                        System.out.println(barang);
                    }
                    System.out.println("\nMenu Checkout:");
                    System.out.println("1. Proses Checkout");
                    System.out.println("2. Batalkan Checkout");
                    System.out.print("Pilih opsi: ");
                    int pilihan = scanner.nextInt();
                    switch (pilihan) {
                        case 1:
                            akun.getBasket().checkout();
                            System.out.println("Checkout berhasil!");
                            return; // Keluar dari metode jika pilihan adalah 1
                        case 2:
                            akun.getBasket().clear();
                            System.out.println("Checkout dibatalkan. Keranjang belanja dikosongkan.");
                            return; // Keluar dari metode jika pilihan adalah 2
                        default:
                            System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                            break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Silakan coba lagi.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        @Override
    public void pilihMetodePembayaran() {
        try {
            System.out.println("\nMetode Pembayaran:");
            System.out.println("1. QRIS");
            System.out.println("2. Transfer Bank");
            System.out.println("3. COD (Cash on Delivery)");
            System.out.print("Pilih metode pembayaran (1-3): ");
            int metodePembayaran = scanner.nextInt();

            // Consume the newline character
            scanner.nextLine();

            switch (metodePembayaran) {
                case 1:
                    akun.setPembayaran(new QRIS(generateRandomId()));
                    break;
                case 2:
                    akun.setPembayaran(new Bank(generateRandomId()));
                    break;
                case 3:
                    akun.setPembayaran(new COD(generateRandomId()));
                    break;
                default:
                    System.out.println("Metode pembayaran tidak valid. Silakan coba lagi.");
                    break;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Input tidak valid. Silakan coba lagi.");
            scanner.nextLine(); // Consume the invalid input
        }
    }


    private String generateRandomId() {
        return String.valueOf((int) (Math.random() * 1000000));
    }
}

public class Main {
    private static Akun akun;
    private static Driver driverAkun;
    private static ListBarang listBarang = new ListBarang(); // Initialize listBarang
    private static Scanner scanner = new Scanner(System.in); // Create Scanner object

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Customer");
            System.out.println("3. Exit");

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
        scanner.close(); // Close the Scanner when the program is finished
    }

    private static void loginAsAdmin() {
        System.out.print("Masukkan ID Admin: ");
        String id = scanner.next();
        System.out.print("Masukkan Nama Admin: ");
        String nama = scanner.next();

        System.out.println("Login berhasil!");
        akun = new Admin(id, nama);
        driverAkun = new AdminDriver((Admin) akun, listBarang);

        // Directly enter the Admin menu
        adminMenu();
    }

    private static void loginAsCustomer() {
        System.out.print("Masukkan ID Customer: ");
        String id = scanner.next();
        System.out.print("Masukkan Nama Customer: ");
        String nama = scanner.next();
    
        System.out.println("Login berhasil!");
        akun = new Customer(id, nama);
        driverAkun = new CustomerDriver((Customer) akun, listBarang, scanner);
    
        // Directly enter the Customer menu
        userMenu();
    }
    
    
    private static void userMenu() {
        boolean userMenuRunning = true;
        while (userMenuRunning) {
            System.out.println("\nUser Menu:");
            System.out.println("1. Kelola Barang");
            System.out.println("2. Beli Barang");
            System.out.println("3. Lihat List Barang");
            System.out.println("4. Checkout");
            System.out.println("5. Pilih Metode Pembayaran");
            System.out.println("0. Logout");

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
                    break;
                case 5:
                    driverAkun.pilihMetodePembayaran();
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

    private static void adminMenu() {
        boolean adminMenuRunning = true;
        while (adminMenuRunning) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Kelola Barang");
            System.out.println("2. Logout");

            System.out.print("Masukkan pilihan: ");
            int adminChoice = scanner.nextInt();

            switch (adminChoice) {
                case 1:
                    driverAkun.kelolaBarang(scanner);
                    break;
                case 2:
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
