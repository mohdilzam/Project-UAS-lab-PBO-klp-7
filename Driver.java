import java.util.Scanner;
import java.util.ArrayList;

/**
 * Kelas abstrak Driver merupakan landasan untuk pembuatan driver dengan peran tertentu.
 * Setiap driver memiliki akses ke akun tertentu dan daftar barang yang dapat dikelola atau dibeli.
 */
abstract class Driver {
    /**
     * Objek Akun yang terkait dengan driver.
     */
    protected Akun akun;

    /**
     * Objek ListBarang yang berisi daftar barang yang dapat dikelola atau dibeli oleh driver.
     */
    protected ListBarang listBarang;

    /**
     * Konstruktor untuk membuat objek Driver dengan Akun dan ListBarang tertentu.
     *
     * @param akun       Objek Akun yang terkait dengan driver.
     * @param listBarang Objek ListBarang yang berisi daftar barang yang dapat dikelola atau dibeli.
     */
    public Driver(Akun akun, ListBarang listBarang) {
        this.akun = akun;
        this.listBarang = listBarang;
    }

    /**
     * Metode abstrak untuk proses login sesuai peran driver.
     */
    public abstract void login();

    /**
     * Metode abstrak untuk mengelola barang, seperti menambah, menghapus, atau mengedit barang.
     *
     * @param scanner Objek Scanner untuk membaca input pengguna.
     */
    public abstract void kelolaBarang(Scanner scanner);

    /**
     * Metode abstrak untuk proses pembelian barang oleh driver.
     */
    public abstract void beliBarang();

    /**
     * Metode abstrak untuk melihat daftar barang yang tersedia.
     */
    public abstract void lihatListBarang();

    /**
     * Metode abstrak untuk proses checkout setelah memilih barang dan metode pembayaran.
     */
    public abstract void checkout();

    /**
     * Metode abstrak untuk memilih metode pembayaran oleh driver.
     */
    public abstract void pilihMetodePembayaran();
}
