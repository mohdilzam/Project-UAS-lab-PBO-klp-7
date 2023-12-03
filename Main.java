import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Barang {
    private String id;
    private String nama;
    private double harga;
    private int jumlah;

    public Barang(String id, String nama, double harga, int jumlahBarang) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.jumlah = jumlahBarang;
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
    private List<Transaksi> listTransaksi;

    public ListBarang() {
        this.listBarang = new ArrayList<>();
        this.listTransaksi = new ArrayList<>();
    }

    public void tambahBarang(Barang barang) {
        listBarang.add(barang);
    }

     public void displayAsTable() {
        System.out.printf("+--------+----------------------+-------------+--------+%n");
        System.out.printf("| ID     | Nama Barang          | Harga       | Jumlah |%n");
        System.out.printf("+--------+----------------------+-------------+--------+%n");

        for (Barang barang : listBarang) {
            System.out.printf("| %-6s | %-20s | %-11.2f | %-6d |%n",
                    barang.getId(), barang.getNama(), barang.getHarga(), barang.getJumlah());
        }

        System.out.println("+--------+----------------------+-------------+--------+");
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
    }

    public void clear() {
        listBarang.clear();
    }

    public List<Transaksi> getTransaksi() {
        return listTransaksi;
    }

    public void setTransaksi(List<Transaksi> transaksiList) {
        this.listTransaksi = transaksiList;
    }


    public Transaksi cariTransaksiById(int idTransaksi) {
    for (Transaksi transaksi : listTransaksi) {
        if (transaksi.getId() == idTransaksi) {
            return transaksi;
        }
    }
    return null;
}

}

class Transaksi {
    private int id;
    private Akun akun;
    private List<Barang> barang;
    private boolean status;

    public Transaksi(Akun akun) {
        this.akun = akun;
        this.barang = new ArrayList<>();
        this.status = false; 
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
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public int getId() {
        return id;
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
    protected Pembayaran pembayaran;

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

    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    public abstract void setPembayaran(Pembayaran pembayaran);

    public void login() {
        System.out.println("Login as: " + getNama());
    }

    public ListBarang getBasket() {
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
        this.pembayaran = pembayaran;
    }
    
}

class Admin extends Akun {
    public Admin(String id, String nama) {
        super(id, nama);
    }

    @Override
    public void setPembayaran(Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
    }
}

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
    private Scanner scanner;   
    public AdminDriver(Admin akun, ListBarang listBarang, Scanner scanner) {
        super(akun, listBarang);
        this.scanner = scanner;
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
                listBarang.displayAsTable();
                
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
                    int jumlahBarang = 0;
                    while (true) {
                        try {
                            System.out.print("Masukkan Jumlah barang: ");
                            jumlahBarang = scanner.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Jumlah tidak valid. Masukkan angka.");
                            scanner.next();
                        }
                    }
                    Barang barang = new Barang(idBarang, namaBarang, hargaBarang, jumlahBarang);
                    barang.setJumlah(jumlahBarang);
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
                        return;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                        break;
                }
            }
        }catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Silakan coba lagi.");
            scanner.nextLine();
        }
    }

    @Override
    public void beliBarang() {
        System.out.println("Admin tidak dapat melakukan pembelian.");
    }

    @Override
    public void lihatListBarang() {
        System.out.println("\nList Barang:");
        for (Barang barang : listBarang.getBarang()) {
            System.out.println(barang);
        }
    }

    @Override
    public void checkout() {
        System.out.println("Admin tidak dapat melakukan checkout.");
    }

    @Override
    public void pilihMetodePembayaran() {
        System.out.println("Admin tidak dapat memilih metode pembayaran.");
    }
        
    public void setujuCheckout() {
    if (listBarang == null || listBarang.getTransaksi() == null) {
        System.out.println("Error: listBarang or transactions list is null");
        return;
    }

    System.out.println("\nTransaksi Menunggu Persetujuan:");
    for (Transaksi transaksi : listBarang.getTransaksi()) {
        if (!transaksi.getStatus()) {
            System.out.println("Transaksi ID: " + transaksi.hashCode());
            System.out.println("Total Belanja: Rp " + calculateTotal(transaksi));
        }
    }

    System.out.print("Masukkan ID Transaksi yang akan disetujui: ");
    int idTransaksi = scanner.nextInt();

    Transaksi transaksi = listBarang.cariTransaksiById(idTransaksi);

    if (transaksi != null && !transaksi.getStatus()) {
    transaksi.setStatus(true);
    System.out.println("Transaksi berhasil disetujui.");
    } else {
        System.out.println("Transaksi berhasil disetujui.");
    }
}

    private double calculateTotal(Transaksi transaksi) {
        double total = 0;
        for (Barang barang : transaksi.getBarang()) {
            total += barang.getHarga() * barang.getJumlah();
        }
        return total;
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
                        return;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Silakan coba lagi.");
            scanner.nextLine();
        }
    }
        @Override
    public void beliBarang() {
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
            System.out.print("Apakah Anda ingin menambahkan barang ke keranjang? (y/n): ");
            String addToBasketChoice = scanner.next().toLowerCase();

            if (addToBasketChoice.equals("y")) {
                ListBarang basket = akun.getBasket();
                if (basket != null) {
                    basket.tambahBarang(barangBeli);
                    System.out.println("Barang berhasil ditambahkan ke keranjang.");
                } else {
                    System.out.println("Error: Basket is null");
                }
            } else {
                System.out.println("Barang tidak ditambahkan ke keranjang.");
            }
        } else {
            System.out.println("Barang dengan ID " + idBarangBeli + " tidak ditemukan.");
        }
    }


    @Override
    public void lihatListBarang() {
        System.out.println("\nList Barang:");
        listBarang.displayAsTable();
    }

        private double calculateTotal() {
            double total = 0;
            for (Barang barang : akun.getBasket().getBarang()) {
                total += barang.getHarga() * barang.getJumlah();
            }
            return total;
        }

    @Override
    public void checkout() {
        try {
            while (true) {
                System.out.println("\nKeranjang Belanja:");
                listBarang.displayAsTable();

                System.out.println("\nMenu Checkout:");
                System.out.println("1. Proses Checkout");
                System.out.println("2. Batalkan Checkout");
                System.out.print("Pilih opsi: ");
                int pilihan = scanner.nextInt();
                switch (pilihan) {
                    case 1:
                        double total = calculateTotal();
                        System.out.println("Total Belanja: Rp " + total);

                        System.out.print("Apakah Anda ingin melanjutkan dengan checkout? (y/n): ");
                        String approvalChoice = scanner.next().toLowerCase();

                        if (approvalChoice.equals("y")) {
                            if (!akun.getBasket().getTransaksi().isEmpty()) {
                            Transaksi transaksi = akun.getBasket().getTransaksi().get(0);
                            transaksi.setStatus(true);
                            System.out.println("Transaksi berhasil disetujui.");
                        } else {
                            System.out.println("Tidak ada transaksi dalam keranjang.");
                        }

                            pilihMetodePembayaran();

                            Transaksi transaksi = new Transaksi(akun);
                            transaksi.setBarang(akun.getBasket().getBarang());

                        
                            Invoice invoice = new Invoice(transaksi, akun.getPembayaran());

                            invoice.cetakInvoice();

                            akun.getBasket().clear();

                            System.out.println("Checkout berhasil!");
                        } else {
                            System.out.println("Checkout dibatalkan.");
                        }
                        return;
                    case 2:
                        akun.getBasket().clear();
                        System.out.println("Checkout dibatalkan. Keranjang belanja dikosongkan.");
                        return;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Silakan coba lagi.");
            scanner.nextLine();
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

                scanner.nextLine();

                switch (metodePembayaran) {
                    case 1:
                        akun.setPembayaran(new QRIS(generateRandomId()));
                        System.out.println("Metode pembayaran berhasil dipilih: QRIS");
                        break;
                    case 2:
                        akun.setPembayaran(new Bank(generateRandomId()));
                        System.out.println("Metode pembayaran berhasil dipilih: Transfer Bank");
                        break;
                    case 3:
                        akun.setPembayaran(new COD(generateRandomId()));
                        System.out.println("Metode pembayaran berhasil dipilih: COD (Cash on Delivery)");
                        break;
                    default:
                        System.out.println("Metode pembayaran tidak valid. Silakan coba lagi.");
                        break;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Input tidak valid. Silakan coba lagi.");
                scanner.nextLine();
            }
        }
    private String generateRandomId() {
        return String.valueOf((int) (Math.random() * 1000000));
    }

    public void lihatInvoice() {
    List<Transaksi> transaksiList = akun.getBasket().getTransaksi();

    if (!transaksiList.isEmpty()) {
        Transaksi transaksi = transaksiList.get(0);

        if (transaksi != null && transaksi.getStatus()) {
            Invoice invoice = new Invoice(transaksi, akun.getPembayaran());

            invoice.cetakInvoice();
        } else {
            System.out.println("Tidak ada transaksi yang telah disetujui.");
        }
    } else {
        System.out.println("Tidak ada transaksi dalam keranjang.");
    }
}

}

public class Main {
    private static Akun akun;
    private static Driver driverAkun;
    private static ListBarang listBarang = new ListBarang();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Login sebagai Admin");
            System.out.println("2. Login sebagai Customer");
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
        scanner.close();
    }
        
    private static void loginAsAdmin() {
        System.out.print("Masukkan ID Admin: ");
        String id = scanner.next();
        System.out.print("Masukkan Password Admin: ");
        String password = scanner.next();
    
        if (id.equals("000") && password.equals("Admin")) {
            System.out.println("Login berhasil!");
            akun = new Admin(id, "Admin");
            driverAkun = new AdminDriver((Admin) akun, listBarang, scanner);
    
            adminMenu();
        } else {
            System.out.println("ID atau Password salah, silahkan coba lagi!");
        }
    }
    
    private static void loginAsCustomer() {
        System.out.print("Masukkan ID Customer: ");
        String id = scanner.next();
        System.out.print("Masukkan Password Customer: ");
        String password = scanner.next();
    
        if (id.equals("001") && password.equals("Customer")) {
            System.out.println("Login berhasil!");
            akun = new Customer(id, "Customer");
            driverAkun = new CustomerDriver((Customer) akun, listBarang, scanner);
    
            userMenu();
        } else {
            System.out.println("ID atau Password salah, silahkan coba lagi!");
        }
    }
    
    
        private static void userMenu() {
        boolean userMenuRunning = true;
        while (userMenuRunning) {
            System.out.println("\nUser Menu:");
            System.out.println("1. Kelola Barang");
            System.out.println("2. Beli Barang");
            System.out.println("3. Lihat List Barang");
            System.out.println("4. Checkout");
            System.out.println("5. Lihat Invoice");
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
                    akun = null;
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

    private static void adminMenu() {
    boolean adminMenuRunning = true;
    while (adminMenuRunning) {
        System.out.println("\nAdmin Menu:");
        System.out.println("1. Kelola Barang");
        System.out.println("2. Setujui Checkout");
        System.out.println("3. Logout");

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
