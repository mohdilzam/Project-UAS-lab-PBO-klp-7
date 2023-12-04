/**
 * Kelas abstrak Akun merupakan kelas dasar untuk objek akun dalam sistem pembelian barang.
 */
abstract class Akun {
    /**
     * ID unik untuk setiap akun.
     */
    private String id;

    /**
     * Nama pemilik akun.
     */
    private String nama;

    /**
     * Objek pembayaran yang terkait dengan akun.
     */
    protected Pembayaran pembayaran;

    /**
     * Konstruktor untuk membuat objek Akun.
     *
     * @param id   ID unik untuk akun.
     * @param nama Nama pemilik akun.
     */
    public Akun(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    /**
     * Mendapatkan ID akun.
     *
     * @return ID akun.
     */
    public String getId() {
        return id;
    }

    /**
     * Mendapatkan nama pemilik akun.
     *
     * @return Nama pemilik akun.
     */
    public String getNama() {
        return nama;
    }

    /**
     * Mengatur nama pemilik akun.
     *
     * @param nama Nama baru untuk akun.
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * Mendapatkan objek pembayaran terkait dengan akun.
     *
     * @return Objek pembayaran terkait dengan akun.
     */
    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    /**
     * Metode abstrak untuk mengatur objek pembayaran terkait dengan akun.
     *
     * @param pembayaran Objek pembayaran yang akan diatur.
     */
    public abstract void setPembayaran(Pembayaran pembayaran);

    /**
     * Metode untuk proses login dan menampilkan informasi pemilik akun yang berhasil login.
     */
    public void login() {
        System.out.println("Login as: " + getNama());
    }

    /**
     * Metode untuk mendapatkan keranjang belanja pengguna (Customer).
     *
     * @return Objek ListBarang yang merupakan keranjang belanja.
     */
    public ListBarang getBasket() {
        // Kembalikan keranjang belanja pengguna (Customer)
        return null;
    }
}
