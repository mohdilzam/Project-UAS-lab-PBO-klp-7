import java.util.*;

/**
 * Kelas yang merepresentasikan driver untuk Admin dalam aplikasi belanja online.
 */
public class AdminDriver extends Driver {
    private Scanner scanner;

    /**
     * Konstruktor untuk membuat objek AdminDriver baru.
     *
     * @param akun       Akun Admin.
     * @param listBarang Daftar barang dalam aplikasi.
     * @param scanner    Scanner untuk input.
     */
    public AdminDriver(Admin akun, ListBarang listBarang, Scanner scanner) {
        super(akun, listBarang);
        this.scanner = scanner;
    }

    /**
     * Metode untuk login sebagai Admin.
     */
    @Override
    public void login() {
        System.out.println("Admin " + akun.getId() + " login.");
    }

    /**
     * Metode untuk mengelola barang oleh Admin.
     *
     * @param scanner Scanner untuk input.
     */
    @Override
    public void kelolaBarang(Scanner scanner) {
        try {
            while (true) {
                System.out.println("\nList Barang:");
                listBarang.displayAsTable();

                System.out.println("\n+---------------------+");
                System.out.println("| Menu Kelola Barang: |");
                System.out.println("| 1. Tambah Barang    |");
                System.out.println("| 2. Hapus Barang     |");
                System.out.println("| 3. Edit Barang      |");
                System.out.println("| 0. Kembali          |");
                System.out.println("+---------------------+");
                System.out.print("Pilih opsi: ");
                int pilihan = scanner.nextInt();
                switch (pilihan) {
                    case 1:
                        // Menambahkan barang baru
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
                        barang.setJumlah(jumlahBarang); // Set the quantity
                        listBarang.tambahBarang(barang);
                        System.out.println("Barang berhasil ditambahkan: " + barang);
                        break;
                    case 2:
                        // Menghapus barang berdasarkan ID
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
                        // Mengedit informasi barang berdasarkan ID
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
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Silakan coba lagi.");
            scanner.nextLine(); // Consume the invalid input
        }
    }

    /**
     * Metode untuk melakukan pembelian barang oleh Admin.
     */
    @Override
    public void beliBarang() {
        // Admin tidak dapat membeli barang
        System.out.println("Admin tidak dapat melakukan pembelian.");
    }

    /**
     * Metode untuk melihat list barang oleh Admin.
     */
    @Override
    public void lihatListBarang() {
        // Admin dapat melihat list barang
        System.out.println("\nList Barang:");
        for (Barang barang : listBarang.getBarang()) {
            System.out.println(barang);
        }
    }

    /**
     * Metode untuk proses checkout oleh Admin.
     */
    @Override
    public void checkout() {
        // Admin tidak dapat melakukan proses checkout
        System.out.println("Admin tidak dapat melakukan checkout.");
    }

    /**
     * Metode untuk memilih metode pembayaran oleh Admin.
     */
    @Override
    public void pilihMetodePembayaran() {
        // Admin tidak dapat memilih metode pembayaran
        System.out.println("Admin tidak dapat memilih metode pembayaran.");
    }

    /**
     * Metode untuk menyetujui checkout.
     */
    public void setujuCheckout() {
        if (listBarang == null || listBarang.getTransaksi() == null) {
            System.out.println("Error: listBarang or transactions list is null");
            return;
        }

        // Menampilkan transaksi yang menunggu persetujuan
        System.out.println("\nTransaksi Menunggu Persetujuan:");
        for (Transaksi transaksi : listBarang.getTransaksi()) {
            if (!transaksi.getStatus()) {
                System.out.println("Transaksi ID: " + transaksi.hashCode());
                System.out.println("Total Belanja: Rp " + calculateTotal(transaksi));
            }
        }

        // Meminta input ID transaksi yang akan disetujui
        System.out.print("Masukkan ID Transaksi yang akan disetujui: ");
        int idTransaksi = scanner.nextInt();

        // Mencari transaksi dalam keranjang
        Transaksi transaksi = listBarang.cariTransaksiById(idTransaksi);

        if (transaksi != null && !transaksi.getStatus()) {
            transaksi.setStatus(true);
            System.out.println("Transaksi berhasil disetujui.");
        } else {
            System.out.println("Transaksi berhasil disetujui.");
        }
    }

    /**
     * Metode untuk menghitung total belanja dari suatu transaksi.
     *
     * @param transaksi Transaksi yang akan dihitung total belanjanya.
     * @return Total belanja.
     */
    private double calculateTotal(Transaksi transaksi) {
        double total = 0;
        for (Barang barang : transaksi.getBarang()) {
            total += barang.getHarga() * barang.getJumlah();
        }
        return total;
    }
}
