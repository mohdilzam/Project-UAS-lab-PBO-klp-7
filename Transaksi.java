import java.util.List;
import java.util.ArrayList;

/**
 * Kelas yang merepresentasikan sebuah transaksi dalam aplikasi belanja online.
 */
public class Transaksi {
    private int id;
    private Akun akun;
    private List<Barang> barang;
    private boolean status;

    /**
     * Konstruktor untuk membuat objek Transaksi baru.
     *
     * @param akun Akun yang melakukan transaksi.
     */
    public Transaksi(Akun akun) {
        this.akun = akun;
        this.barang = new ArrayList<>();
        this.status = false;
    }

    /**
     * Mengatur daftar barang yang terlibat dalam transaksi.
     *
     * @param barang Daftar barang yang akan diatur.
     */
    public void setBarang(List<Barang> barang) {
        this.barang = barang;
    }

    /**
     * Mendapatkan daftar barang yang terlibat dalam transaksi.
     *
     * @return Daftar barang.
     */
    public List<Barang> getBarang() {
        return barang;
    }

    /**
     * Mendapatkan status transaksi.
     *
     * @return Status transaksi (true jika telah selesai, false jika belum selesai).
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Mengatur status transaksi.
     *
     * @param status Status transaksi yang akan diatur.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Mendapatkan ID transaksi.
     *
     * @return ID transaksi.
     */
    public int getId() {
        return id;
    }

    /**
     * Representasi string dari objek Transaksi.
     *
     * @return Representasi string dari objek Transaksi.
     */
    @Override
    public String toString() {
        return "Transaksi{" + "akun=" + akun.getId() + ", barang=" + barang + '}';
    }
}
