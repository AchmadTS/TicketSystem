package controller.create;

import java.time.LocalDateTime;
import controller.SistemTiket;
import model.*;
import view.AddPesananView;
import util.Helper;

public class PesanTiket {
    SistemTiket sistemTiket;
    AddPesananView view;
    public PesanTiket(SistemTiket sistemTiket) {
        this.sistemTiket = sistemTiket;
        this.view = new AddPesananView();
    }

    public void run() {
        sistemTiket.view.showDaftarPenerbangan(sistemTiket.daftarPenerbangan, sistemTiket.jumlahPenerbangan); // Menampilkan daftar penerbangan yang ada

        // Validasi input ID penerbangan
        Penerbangan penerbangan = null;
        while (penerbangan == null) {
            int id = Helper.inputId(sistemTiket.input, "ID penerbangan: ");
            penerbangan = sistemTiket.cariById(id);
            if (penerbangan == null) {
                System.out.println("❌ ID Tidak ada");
            }
        }

        String nama = Helper.inputStringWajib(sistemTiket.input, "Nama pemesan: "); // Input data pemesan
        int jumlah = 0;

        // Validasi jumlah tiket dengan kursi tersedia
        boolean jumlahValid = false;
        while (!jumlahValid) {
            jumlah = Helper.inputInteger(sistemTiket.input, "Jumlah tiket: ", 1, Integer.MAX_VALUE);
            if (jumlah > penerbangan.jumlahKursi) {
                System.out.println("❌ Kursi tidak cukup! Tersedia: " + penerbangan.jumlahKursi + " kursi");
            } else {
                jumlahValid = true;
            }
        }

        // Update kursi & simpan pemesanan
        penerbangan.jumlahKursi -= jumlah;
        double total = jumlah * penerbangan.harga;
        sistemTiket.daftarPemesanan[sistemTiket.jumlahPemesanan++] = new Pemesanan(sistemTiket.nextIdPemesanan++, penerbangan.id, nama, jumlah, total, LocalDateTime.now());
        view.showPemesanan(penerbangan, nama, jumlah, total);
    }
}