import java.util.*;

/**
 * Kelas yang merepresentasikan driver untuk Customer dalam aplikasi belanja online.
 */
public class CustomerDriver extends Driver {
    private Scanner scanner;

    /**
     * Konstruktor untuk membuat objek CustomerDriver baru.
     *
     * @param akun       Akun Customer.
     * @param listBarang Daftar barang dalam aplikasi.
     * @param scanner    Scanner untuk input.
     */
    public CustomerDriver(Akun akun, ListBarang listBarang, Scanner scanner) {
        super(akun, listBarang);
        this.scanner = scanner;
    }

    /**
     * Metode untuk login sebagai Customer.
     */
    @Override
    public void login() {
        System.out.println("Customer " + akun.getId() + " login.");
    }

    /**
     * Metode untuk mengelola barang oleh Customer.
     *
     * @param scanner Scanner untuk input.
     */
    @Override
    public void kelolaBarang(Scanner scanner) {
        try {
            while (true) {
                System.out.println("\nList Barang:");
                for (Barang barang : listBarang.getBarang()) {
                    System.out.println(barang);
                }
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
                        // Menambahkan barang ke keranjang
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
                        // Menghapus barang dari keranjang
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
                        // Mengedit jumlah barang dalam keranjang
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
            scanner.nextLine(); // Menelan input yang tidak valid
        }
    }

    /**
     * Metode untuk melakukan pembelian barang oleh Customer.
     */
    @Override
    public void beliBarang() {
        // Pembelian barang oleh Customer
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
            // Pilihan untuk menambahkan barang ke keranjang
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

    /**
     * Metode untuk melihat list barang oleh Customer.
     */
    @Override
    public void lihatListBarang() {
        // Customer dapat melihat list barang
        System.out.println("\nList Barang:");
        listBarang.displayAsTable();
    }

    /**
     * Metode untuk menghitung total belanja di keranjang Customer.
     *
     * @return Total belanja di keranjang.
     */
    private double calculateTotal() {
        double total = 0;
        for (Barang barang : akun.getBasket().getBarang()) {
            total += barang.getHarga() * barang.getJumlah();
        }
        return total;
    }

    /**
     * Metode untuk melakukan proses checkout oleh Customer.
     */
    @Override
    public void checkout() {
        try {
            while (true) {
                System.out.println("\nKeranjang Belanja:");
                listBarang.displayAsTable();

                System.out.println("\n+----------------------+");
                System.out.println("| Menu Checkout:       |");
                System.out.println("| 1. Proses Checkout   |");
                System.out.println("| 2. Batalkan Checkout |");
                System.out.println("+----------------------+");
                System.out.print("Pilih opsi: ");
                int pilihan = scanner.nextInt();
                switch (pilihan) {
                    case 1:
                        // Proses checkout oleh Customer
                        double total = calculateTotal();
                        System.out.println("Total Belanja: Rp " + total);

                        // Meminta persetujuan admin
                        System.out.print("Apakah Anda ingin melanjutkan dengan checkout? (y/n): ");
                        String approvalChoice = scanner.next().toLowerCase();

                        if (approvalChoice.equals("y")) {
                            // Set status transaksi menjadi disetujui
                            if (!akun.getBasket().getTransaksi().isEmpty()) {
                                Transaksi transaksi = akun.getBasket().getTransaksi().get(0);
                                transaksi.setStatus(true);
                                System.out.println("Transaksi berhasil disetujui.");
                            } else {
                                System.out.println("Tidak ada transaksi dalam keranjang.");
                            }

                            // Melanjutkan ke proses pembayaran
                            pilihMetodePembayaran();

                            Transaksi transaksi = new Transaksi(akun);  // Membuat transaksi baru terkait dengan akun
                            transaksi.setBarang(akun.getBasket().getBarang());  // Mengatur barang yang dibeli untuk transaksi

                            // Membuat invoice
                            Invoice invoice = new Invoice(transaksi, akun.getPembayaran());

                            // Mencetak invoice
                            invoice.cetakInvoice();

                            // Mengosongkan keranjang setelah checkout berhasil
                            akun.getBasket().clear();

                            System.out.println("Checkout berhasil!");
                        } else {
                            System.out.println("Checkout dibatalkan.");
                        }
                        return; // Keluar dari metode jika pilihan adalah 1
                    case 2:
                        // Membatalkan checkout dan mengosongkan keranjang
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
            scanner.nextLine(); // Menelan input yang tidak valid
        }
    }

    /**
     * Metode untuk memilih metode pembayaran oleh Customer.
     */
    @Override
    public void pilihMetodePembayaran() {
        try {
            // Menampilkan opsi metode pembayaran
            System.out.println("\n+---------------------------+");
            System.out.println("| Metode Pembayaran:        |");
            System.out.println("| 1. QRIS                   |");
            System.out.println("| 2. Transfer Bank          |");
            System.out.println("| 3. COD (Cash on Delivery) |");
            System.out.println("+---------------------------+");
            System.out.print("Pilih metode pembayaran (1-3): ");
            int metodePembayaran = scanner.nextInt();

            // Menelan karakter newline
            scanner.nextLine();

            // Memproses pilihan metode pembayaran
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
            scanner.nextLine(); // Menelan input yang tidak valid
        }
    }

    /**
     * Metode untuk menghasilkan ID acak.
     *
     * @return ID acak dalam bentuk string.
     */
    private String generateRandomId() {
        return String.valueOf((int) (Math.random() * 1000000));
    }

    /**
     * Metode untuk melihat invoice dari transaksi yang telah disetujui.
     */
    public void lihatInvoice() {
        List<Transaksi> transaksiList = akun.getBasket().getTransaksi();

        if (!transaksiList.isEmpty()) {
            Transaksi transaksi = transaksiList.get(0); // Anda mungkin perlu menentukan indeks

            if (transaksi != null && transaksi.getStatus()) {
                // Membuat objek Invoice baru
                Invoice invoice = new Invoice(transaksi, akun.getPembayaran());

                // Mencetak invoice
                invoice.cetakInvoice();
            } else {
                System.out.println("Tidak ada transaksi yang telah disetujui.");
            }
        } else {
            System.out.println("Tidak ada transaksi dalam keranjang.");
        }
    }
}
