import java.util.*;

/**
 * Kelas Keranjang merepresentasikan keranjang belanja yang berisi daftar barang.
 */
class Keranjang {
    /**
     * List yang menyimpan barang-barang dalam keranjang.
     */
    private List<Barang> barang;

    /**
     * Konstruktor untuk membuat objek Keranjang baru.
     * Inisialisasi list barang.
     */
    public Keranjang() {
        this.barang = new ArrayList<>();
    }

    /**
     * Metode untuk menambahkan barang ke dalam keranjang.
     *
     * @param barang Objek Barang yang akan ditambahkan ke dalam keranjang.
     */
    public void tambahBarang(Barang barang) {
        this.barang.add(barang);
    }

    /**
     * Metode untuk mendapatkan daftar barang dalam keranjang.
     *
     * @return List berisi barang-barang dalam keranjang.
     */
    public List<Barang> getBarang() {
        return barang;
    }
}
