/**
 * Kelas Customer merupakan turunan dari kelas Akun yang merepresentasikan pengguna dengan peran sebagai pelanggan.
 * Setiap pelanggan memiliki keranjang belanja (basket) yang dapat diisi dengan barang-barang yang akan dibeli.
 */
class Customer extends Akun {
    /**
     * Keranjang belanja pelanggan berupa objek ListBarang.
     */
    private ListBarang basket;

    /**
     * Konstruktor untuk membuat objek Customer dengan ID dan nama tertentu.
     *
     * @param id   ID unik pelanggan.
     * @param nama Nama pelanggan.
     */
    public Customer(String id, String nama) {
        super(id, nama);
        this.basket = new ListBarang();
    }

    /**
     * Mendapatkan keranjang belanja pelanggan.
     *
     * @return Keranjang belanja pelanggan.
     */
    public ListBarang getBasket() {
        return basket;
    }

    /**
     * Mengeset metode pembayaran untuk pelanggan.
     *
     * @param pembayaran Objek Pembayaran yang akan di-set.
     */
    @Override
    public void setPembayaran(Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
        // Implementasi untuk Customer
    }
}
