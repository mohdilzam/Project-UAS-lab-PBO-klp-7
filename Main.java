import java.util.ArrayList;
import java.util.Scanner;

/**
 * Kelas yang merepresentasikan akun pengguna.
 */
class Akun {
    private String id;
    
    /**
     * Konstruktor untuk membuat objek Akun.
     *
     * @param id ID akun.
     */
    public Akun(String id) {
        this.id = id;
    }

    /**
     * Mendapatkan ID akun.
     *
     * @return ID akun.
     */
    public String getId() {
        return id;
    }
}

//Interface Driver yang menentukan metode login.
interface Driver {
    void login();
}

/**
 * Kelas AdminDriver yang mengimplementasikan interface Driver.
 * Digunakan oleh admin untuk mengelola barang di sistem.
 */
class AdminDriver implements Driver {
    private Akun akun;
    private ListBarang listBarang;

    /**
     * Konstruktor untuk membuat objek AdminDriver.
     *
     * @param akun       Objek Akun admin.
     * @param listBarang Objek ListBarang yang berisi daftar barang.
     */
    public AdminDriver(Akun akun, ListBarang listBarang) {
        this.akun = akun;
        this.listBarang = listBarang;
    }

    @Override
    public void login() {
        System.out.println("Admin " + akun.getId() + " login.");
    }

    //Menampilkan daftar barang.
    public void lihatBarang() {
        System.out.println("\nList Barang:");
        for (Barang barang : listBarang.getBarang()) {
            System.out.println(barang);
        }
    }

    //Menu untuk mengelola barang (tambah, hapus, edit).
    public void kelolaBarang() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nMenu Kelola Barang:");
                System.out.println("1. Tambah Barang");
                System.out.println("2. Hapus Barang");
                System.out.println("3. Edit Barang");
                System.out.println("0. Kembali");
                System.out.print("Pilih opsi: ");
                int opsi = scanner.nextInt();

                switch (opsi) {
                    case 1:
                        tambahBarang();
                        break;
                    case 2:
                        hapusBarang();
                        break;
                    case 3:
                        editBarang();
                        break;
                    case 0:
                        System.out.println("Kembali ke Menu Utama.");
                        return;
                    default:
                        System.out.println("Opsi tidak valid. Silakan coba lagi.");
                }
            }
        }
    }

    //menambah barang ke daftar
    private void tambahBarang() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Masukkan ID barang: ");
            String idBarang = scanner.next();
            System.out.print("Masukkan Nama barang: ");
            String namaBarang = scanner.next();
            System.out.print("Masukkan Harga barang: ");
            double hargaBarang = scanner.nextDouble();

            Barang barang = new Barang(idBarang, namaBarang, hargaBarang);
            listBarang.tambahBarang(barang);
            System.out.println("Barang berhasil ditambahkan: " + barang);
        }
    }

    //Menghapus barang dari daftar
    private void hapusBarang() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Masukkan ID barang yang akan dihapus: ");
            String idBarang = scanner.next();

            Barang barang = listBarang.hapusBarang(idBarang);
            if (barang != null) {
                System.out.println("Barang berhasil dihapus: " + barang);
            } else {
                System.out.println("Barang dengan ID " + idBarang + " tidak ditemukan.");
            }
        }
    }

    //Mengedit barang di daftar
    private void editBarang() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Masukkan ID barang yang akan diedit: ");
            String idBarang = scanner.next();

            Barang barang = listBarang.cariBarangById(idBarang);
            if (barang != null) {
                System.out.print("Masukkan Nama barang baru: ");
                String namaBarang = scanner.next();
                System.out.print("Masukkan Harga barang baru: ");
                double hargaBarang = scanner.nextDouble();

                barang.setNama(namaBarang);
                barang.setHarga(hargaBarang);
                System.out.println("Barang berhasil diedit: " + barang);
            } else {
                System.out.println("Barang dengan ID " + idBarang + " tidak ditemukan.");
            }
        }
    }
}

/**
 * Kelas CustomerDriver yang mengimplementasikan interface Driver.
 * Digunakan oleh customer untuk melakukan pembelian barang.
 */
class CustomerDriver implements Driver {
    private Akun akun;
    private Keranjang keranjang;
    private Transaksi transaksi;
    private ListBarang listBarang;
    private ArrayList<Transaksi> historyBelanja;

    /**
     * Konstruktor untuk membuat objek CustomerDriver.
     *
     * @param akun       Objek Akun customer.
     * @param listBarang Objek ListBarang yang berisi daftar barang.
     */
    public CustomerDriver(Akun akun, ListBarang listBarang) {
        this.akun = akun;
        this.keranjang = new Keranjang();
        this.transaksi = new Transaksi(akun);
        this.listBarang = listBarang;
        this.historyBelanja = new ArrayList<>();
    }

    @Override
    public void login() {
        System.out.println("Customer " + akun.getId() + " login.");
    }

    //Menampilkan daftar barang
    public void lihatBarang() {
        System.out.println("\nList Barang:");
        for (Barang barang : listBarang.getBarang()) {
            System.out.println(barang);
        }
    }

    //Menu untuk mengelola keranjang (tambah, hapus, lihat, checkout)
    public void kelolaKeranjang() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nMenu Keranjang:");
                System.out.println("1. Tambah Barang ke Keranjang");
                System.out.println("2. Hapus Barang dari Keranjang");
                System.out.println("3. Lihat Keranjang");
                System.out.println("0. Kembali");
                System.out.print("Pilih opsi: ");
                int opsi = scanner.nextInt();

                switch (opsi) {
                    case 1:
                        tambahKeKeranjang();
                        break;
                    case 2:
                        hapusDariKeranjang();
                        break;
                    case 3:
                        lihatKeranjang();
                        break;
                    case 0:
                        System.out.println("Kembali ke Menu Utama.");
                        return;
                    default:
                        System.out.println("Opsi tidak valid. Silakan coba lagi.");
                }
            }
        }
    }

    //Menambahkan barang ke keranjang
    private void tambahKeKeranjang() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Masukkan ID barang yang akan ditambahkan ke keranjang: ");
            String idBarang = scanner.next();

            Barang barang = listBarang.cariBarangById(idBarang);
            if (barang != null) {
                keranjang.tambahBarang(barang);
                System.out.println("Barang berhasil ditambahkan ke keranjang: " + barang);
            } else {
                System.out.println("Barang dengan ID " + idBarang + " tidak ditemukan.");
            }
        }
    }

    //Menghapus barang dari keranjang
    private void hapusDariKeranjang() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Masukkan ID barang yang akan dihapus dari keranjang: ");
            String idBarang = scanner.next();

            Barang barang = keranjang.hapusBarang(idBarang);
            if (barang != null) {
                System.out.println("Barang berhasil dihapus dari keranjang: " + barang);
            } else {
                System.out.println("Barang dengan ID " + idBarang + " tidak ditemukan di keranjang.");
            }
        }
    }

    //Menampilkan isi keranjang
    private void lihatKeranjang() {
        System.out.println("\nIsi Keranjang:");
        for (Barang barang : keranjang.getBarang()) {
            System.out.println(barang);
        }
    }

    //Melakukan proses checkout
    public void checkout() {
        if (keranjang.getBarang().isEmpty()) {
            System.out.println("Keranjang kosong. Tidak dapat melakukan checkout.");
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Metode Pembayaran:");
            System.out.println("1. QRIS");
            System.out.println("2. Bank");
            System.out.println("3. COD (Cash on Delivery)");
            System.out.print("Pilih metode pembayaran: ");
            int metodePembayaran = scanner.nextInt();

            Pembayaran pembayaran = null;

            switch (metodePembayaran) {
                case 1:
                    pembayaran = new QRIS("QR001");
                    break;
                case 2:
                    pembayaran = new Bank("BCA");
                    break;
                case 3:
                    pembayaran = new COD("COD001");
                    break;
                default:
                    System.out.println("Metode pembayaran tidak valid.");
                    return;
            }

            transaksi.setBarang(keranjang.getBarang());
            Invoice invoice = new Invoice(transaksi, pembayaran);
            historyBelanja.add(transaksi);
            keranjang.clear();
            System.out.println("Checkout berhasil!");
            System.out.println("Invoice:");
            System.out.println(invoice);
        }
    }

    //Menampilkan history belanja customer
    public void lihatHistoryBelanja() {
        System.out.println("\nHistory Belanja:");
        for (Transaksi transaksi : historyBelanja) {
            System.out.println(transaksi);
        }
    }
}

/**
 * Kelas Keranjang yang menyimpan daftar barang yang akan dibeli oleh customer.
 */
class Keranjang {
    private ArrayList<Barang> barang;

    //Konstruktor untuk membuat objek keranjang
    public Keranjang() {
        this.barang = new ArrayList<>();
    }

    /**
     * Menambah barang ke dalam keranjang.
     *
     * @param barang Barang yang akan ditambahkan.
     */
    public void tambahBarang(Barang barang) {
        this.barang.add(barang);
    }

    /**
     * Menghapus barang dari keranjang berdasarkan ID barang.
     *
     * @param idBarang ID barang yang akan dihapus.
     * @return Barang yang dihapus, atau null jika tidak ditemukan.
     */
    public Barang hapusBarang(String idBarang) {
        for (Barang barang : barang) {
            if (barang.getId().equals(idBarang)) {
                this.barang.remove(barang);
                return barang;
            }
        }
        return null;
    }

    /**
     * Mendapatkan daftar barang dalam keranjang.
     *
     * @return Daftar barang dalam keranjang.
     */
    public ArrayList<Barang> getBarang() {
        return barang;
    }

    //Mengosongkan isi keranjang
    public void clear() {
        barang.clear();
    }
}

/**
 * Kelas ListBarang yang menyimpan daftar barang yang tersedia.
 */
class ListBarang {
    private ArrayList<Barang> barang;

    //Konstruktor untuk membuat objek ListBarang dengan menambahkan barang dummy(data buatan).
    public ListBarang() {
        this.barang = new ArrayList<>();
        // Menambahkan barang dummy
        tambahBarang(new Barang("B001", "Laptop", 5000000));
        tambahBarang(new Barang("B002", "Smartphone", 2000000));
    }

    /**
     * Menambah barang ke dalam daftar barang.
     *
     * @param barang Barang yang akan ditambahkan.
     */
    public void tambahBarang(Barang barang) {
        this.barang.add(barang);
    }

    /**
     * Menghapus barang dari daftar berdasarkan ID barang.
     *
     * @param idBarang ID barang yang akan dihapus.
     * @return Barang yang dihapus, atau null jika tidak ditemukan.
     */
    public Barang hapusBarang(String idBarang) {
        Barang barangDihapus = cariBarangById(idBarang);
        if (barangDihapus != null) {
            this.barang.remove(barangDihapus);
        }
        return barangDihapus;
    }

    /**
     * Mencari barang dalam daftar berdasarkan ID barang.
     *
     * @param idBarang ID barang yang akan dicari.
     * @return Barang yang ditemukan, atau null jika tidak ditemukan.
     */
    public Barang cariBarangById(String idBarang) {
        for (Barang barang : barang) {
            if (barang.getId().equals(idBarang)) {
                return barang;
            }
        }
        return null;
    }

    /**
     * Mendapatkan daftar barang.
     *
     * @return Daftar barang.
     */
    public ArrayList<Barang> getBarang() {
        return barang;
    }
}

/**
 * Kelas Barang yang merepresentasikan sebuah barang yang akan dijual.
 */
class Barang {
    private String id;
    private String nama;
    private double harga;

    /**
     * Konstruktor untuk membuat objek Barang.
     *
     * @param id    ID barang.
     * @param nama  Nama barang.
     * @param harga Harga barang.
     */
    public Barang(String id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }

    /**
     * Getter untuk mendapatkan ID barang.
     *
     * @return ID barang.
     */
    public String getId() {
        return id;
    }

    /**
     * Getter untuk mendapatkan nama barang.
     *
     * @return Nama barang.
     */
    public String getNama() {
        return nama;
    }

    /**
     * Setter untuk mengatur nama barang.
     *
     * @param nama Nama barang yang baru.
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * Getter untuk mendapatkan harga barang.
     *
     * @return Harga barang.
     */
    public double getHarga() {
        return harga;
    }

    /**
     * Setter untuk mengatur harga barang.
     *
     * @param harga Harga barang yang baru.
     */
    public void setHarga(double harga) {
        this.harga = harga;
    }

    /**
     * Override dari metode toString untuk representasi string objek Barang.
     *
     * @return String representasi objek Barang.
     */
    @Override
    public String toString() {
        return "Barang{" +
                "id='" + id + '\'' +
                ", nama='" + nama + '\'' +
                ", harga=" + harga +
                '}';
    }
}

/**
 * Kelas untuk merepresentasikan sebuah transaksi yang dilakukan oleh pengguna.
 */
class Transaksi {
    private Akun akun;
    private ArrayList<Barang> barang;

    /**
     * Konstruktor untuk membuat objek Transaksi.
     *
     * @param akun Objek akun yang melakukan transaksi.
     */
    public Transaksi(Akun akun) {
        this.akun = akun;
        this.barang = new ArrayList<>();
    }

    /**
     * Setter untuk mengatur daftar barang dalam transaksi.
     *
     * @param barang Daftar barang dalam transaksi.
     */
    public void setBarang(ArrayList<Barang> barang) {
        this.barang = barang;
    }

    /**
     * Getter untuk mendapatkan daftar barang dalam transaksi.
     *
     * @return Daftar barang dalam transaksi.
     */
    public ArrayList<Barang> getBarang() {
        return barang;
    }

    /**
     * Override dari metode toString untuk representasi string objek Transaksi.
     *
     * @return String representasi objek Transaksi.
     */
    @Override
    public String toString() {
        return "Transaksi{" +
                "akun=" + akun.getId() +
                ", barang=" + barang +
                '}';
    }
}

/**
 * Kelas untuk merepresentasikan sebuah invoice dari transaksi yang dilakukan.
 */
class Invoice {
    private Transaksi transaksi;
    private Pembayaran pembayaran;

    /**
     * Konstruktor untuk membuat objek Invoice.
     *
     * @param transaksi   Objek transaksi yang di-invoice.
     * @param pembayaran  Objek pembayaran untuk transaksi ini.
     */
    public Invoice(Transaksi transaksi, Pembayaran pembayaran) {
        this.transaksi = transaksi;
        this.pembayaran = pembayaran;
    }

    //Metode untuk mencetak invoice ke layar
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

/**
 * Interface untuk merepresentasikan metode pembayaran.
 */
interface Pembayaran {
    String getId();
}

/**
 * Kelas untuk merepresentasikan metode pembayaran menggunakan QRIS.
 */
class QRIS implements Pembayaran {
    private String id;

    /**
     * Konstruktor untuk membuat objek QRIS.
     *
     * @param id ID QRIS.
     */
    public QRIS(String id) {
        this.id = id;
    }

    /**
     * Getter untuk mendapatkan ID QRIS.
     *
     * @return ID QRIS.
     */
    @Override
    public String getId() {
        return id;
    }
}

/**
 * Kelas untuk merepresentasikan metode pembayaran menggunakan Bank.
 */
class Bank implements Pembayaran {
    private String id;

    /**
     * Konstruktor untuk membuat objek Bank.
     *
     * @param id ID Bank.
     */
    public Bank(String id) {
        this.id = id;
    }

    /**
     * Getter untuk mendapatkan ID Bank.
     *
     * @return ID Bank.
     */
    @Override
    public String getId() {
        return id;
    }
}

/**
 * Kelas untuk merepresentasikan metode pembayaran menggunakan COD (Cash on Delivery).
 */
class COD implements Pembayaran {
    private String id;

    /**
     * Konstruktor untuk membuat objek COD.
     *
     * @param id ID COD.
     */
    public COD(String id) {
        this.id = id;
    }

    /**
     * Getter untuk mendapatkan ID COD.
     *
     * @return ID COD.
     */
    @Override
    public String getId() {
        return id;
    }
}

/**
 * Kelas utama yang berisi metode main untuk menjalankan program.
 */
public class Main {
    public static void main(String[] args) {
        //Implementasi metode main
        try (Scanner scanner = new Scanner(System.in)) {
            Akun akun = null;
            Driver driverAkun = null;
            ListBarang listBarang = new ListBarang();
            listBarang.tambahBarang(new Barang("B001", "Laptop", 5000000));
            listBarang.tambahBarang(new Barang("B002", "Smartphone", 2000000));

            while (true) {
                System.out.println("Selamat datang di Sistem Perbelanjaan Online!");
                System.out.println("1. Login");
                System.out.println("0. Keluar");
                System.out.print("Pilih opsi: ");
                int pilihan = scanner.nextInt();

                if (pilihan == 1) {
                    System.out.print("Masukkan ID akun: ");
                    String idAkun = scanner.next();
                    System.out.print("Pilih jenis akun (Admin/Customer): ");
                    String jenisAkun = scanner.next();

                    if (jenisAkun.equals("Admin")) {
                        akun = new Akun(idAkun);
                        driverAkun = new AdminDriver(akun, listBarang);
                    } else if (jenisAkun.equals("Customer")) {
                        akun = new Akun(idAkun);
                        driverAkun = new CustomerDriver(akun, listBarang);
                    } else {
                        System.out.println("Jenis akun tidak valid. Silakan coba lagi.");
                        continue;
                    }

                    driverAkun.login();

                    if (driverAkun instanceof AdminDriver) {
                        ((AdminDriver) driverAkun).lihatBarang();
                        ((AdminDriver) driverAkun).kelolaBarang();
                    } else if (driverAkun instanceof CustomerDriver) {
                        ((CustomerDriver) driverAkun).lihatBarang();
                        ((CustomerDriver) driverAkun).kelolaKeranjang();
                        ((CustomerDriver) driverAkun).checkout();
                        ((CustomerDriver) driverAkun).lihatHistoryBelanja();
                    }
                } else if (pilihan == 0) {
                    System.out.println("Terima kasih! Program selesai.");
                    break;
                } else {
                    System.out.println("Opsi tidak valid. Silakan coba lagi.");
                }
            }
        }
    }
}

