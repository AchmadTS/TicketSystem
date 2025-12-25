package controller.create;

import java.time.LocalDateTime;

import controller.SistemTiket;
import model.*;
import view.AddPesananView;
import util.Helper;

public class PesanTiket {
    public static void run(SistemTiket sistemTiket) {
        AddPesananView view = new AddPesananView();
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

        // Cek apakah masih ada kursi tersedia
        if (penerbangan.jumlahKursi == 0) {
            System.out.println("❌ Maaf, penerbangan ini sudah penuh. Tidak ada kursi tersedia.");
            return;
        }

        String nama = Helper.inputStringWajib(sistemTiket.input, "Nama pemesan: ");
        int jumlah = Helper.inputInteger(sistemTiket.input, "Jumlah tiket (max " + penerbangan.jumlahKursi + "): ", 1, penerbangan.jumlahKursi);

        // Update kursi & simpan pemesanan
        penerbangan.jumlahKursi -= jumlah;
        double total = jumlah * penerbangan.harga;
        sistemTiket.daftarPemesanan[sistemTiket.jumlahPemesanan++] = new Pemesanan(sistemTiket.nextIdPemesanan++, penerbangan.id, nama, jumlah, total, LocalDateTime.now());
        view.showPemesanan(penerbangan, nama, jumlah, total);
    }
}