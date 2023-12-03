/**
 * Kelas Invoice merepresentasikan faktur atau kwitansi untuk sebuah transaksi pembelian.
 * Faktur ini mencakup informasi mengenai transaksi dan metode pembayaran yang digunakan.
 */
class Invoice {
    /**
     * Objek Transaksi yang terkait dengan faktur ini.
     */
    private Transaksi transaksi;

    /**
     * Objek Pembayaran yang terkait dengan faktur ini.
     */
    private Pembayaran pembayaran;

    /**
     * Konstruktor untuk membuat objek Invoice dengan Transaksi dan Pembayaran tertentu.
     *
     * @param transaksi   Objek Transaksi yang terkait dengan faktur ini.
     * @param pembayaran Objek Pembayaran yang terkait dengan faktur ini.
     */
    public Invoice(Transaksi transaksi, Pembayaran pembayaran) {
        this.transaksi = transaksi;
        this.pembayaran = pembayaran;
    }

    /**
     * Metode untuk mencetak informasi faktur ke konsol.
     * Informasi mencakup ID transaksi, metode pembayaran, dan daftar barang yang dibeli.
     */
    public void cetakInvoice() {
        System.out.println("======== INVOICE ========");
        System.out.println("Transaksi ID: " + transaksi.hashCode());
        System.out.println("Pembayaran: " + pembayaran.getId());
        System.out.println("Barang yang dibeli:");
        for (Barang barang : transaksi.getBarang()) {
            System.out.println("- " + barang.getNama() + " - Rp " + barang.getHarga());
        }
        System.out.println("=========================");
    }
}
