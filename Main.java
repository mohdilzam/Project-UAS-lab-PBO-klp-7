import java.util.ArrayList;
import java.util.Scanner;

class Akun {
    private String id;

    public Akun(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

interface Driver {
    void login();
}

class AdminDriver implements Driver {
    private Akun akun;
    private ListBarang listBarang;

    public AdminDriver(Akun akun, ListBarang listBarang) {
        this.akun = akun;
        this.listBarang = listBarang;
    }

    @Override
    public void login() {
        System.out.println("Admin " + akun.getId() + " login.");
    }

    public void lihatBarang() {
        System.out.println("\nList Barang:");
        for (Barang barang : listBarang.getBarang()) {
            System.out.println(barang);
        }
    }

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

class CustomerDriver implements Driver {
    private Akun akun;
    private Keranjang keranjang;
    private Transaksi transaksi;
    private ListBarang listBarang;
    private ArrayList<Transaksi> historyBelanja;

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

    public void lihatBarang() {
        System.out.println("\nList Barang:");
        for (Barang barang : listBarang.getBarang()) {
            System.out.println(barang);
        }
    }

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

    private void lihatKeranjang() {
        System.out.println("\nIsi Keranjang:");
        for (Barang barang : keranjang.getBarang()) {
            System.out.println(barang);
        }
    }

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

    public void lihatHistoryBelanja() {
        System.out.println("\nHistory Belanja:");
        for (Transaksi transaksi : historyBelanja) {
            System.out.println(transaksi);
        }
    }
}

class Keranjang {
    private ArrayList<Barang> barang;

    public Keranjang() {
        this.barang = new ArrayList<>();
    }

    public void tambahBarang(Barang barang) {
        this.barang.add(barang);
    }

    public Barang hapusBarang(String idBarang) {
        for (Barang barang : barang) {
            if (barang.getId().equals(idBarang)) {
                this.barang.remove(barang);
                return barang;
            }
        }
        return null;
    }

    public ArrayList<Barang> getBarang() {
        return barang;
    }

    public void clear() {
        barang.clear();
    }
}

class ListBarang {
    private ArrayList<Barang> barang;

    public ListBarang() {
        this.barang = new ArrayList<>();
        // Menambahkan barang dummy
        tambahBarang(new Barang("B001", "Laptop", 5000000));
        tambahBarang(new Barang("B002", "Smartphone", 2000000));
    }

    public void tambahBarang(Barang barang) {
        this.barang.add(barang);
    }

    public Barang hapusBarang(String idBarang) {
        Barang barangDihapus = cariBarangById(idBarang);
        if (barangDihapus != null) {
            this.barang.remove(barangDihapus);
        }
        return barangDihapus;
    }

    public Barang cariBarangById(String idBarang) {
        for (Barang barang : barang) {
            if (barang.getId().equals(idBarang)) {
                return barang;
            }
        }
        return null;
    }

    public ArrayList<Barang> getBarang() {
        return barang;
    }
}

class Barang {
    private String id;
    private String nama;
    private double harga;

    public Barang(String id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
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

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        return "Barang{" +
                "id='" + id + '\'' +
                ", nama='" + nama + '\'' +
                ", harga=" + harga +
                '}';
    }
}

class Transaksi {
    private Akun akun;
    private ArrayList<Barang> barang;

    public Transaksi(Akun akun) {
        this.akun = akun;
        this.barang = new ArrayList<>();
    }

    public void setBarang(ArrayList<Barang> barang) {
        this.barang = barang;
    }

    public ArrayList<Barang> getBarang() {
        return barang;
    }

    @Override
    public String toString() {
        return "Transaksi{" +
                "akun=" + akun.getId() +
                ", barang=" + barang +
                '}';
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

public class Main {
    public static void main(String[] args) {
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

