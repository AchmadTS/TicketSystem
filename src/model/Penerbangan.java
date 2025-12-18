package model;

import java.text.DecimalFormat;
import util.Helper;

public class Penerbangan {
    public int id;
    public String pesawat;
    public String asal;
    public String tujuan;
    public double harga;
    public int bulan;
    public int hari;
    public int tahun;
    public int jam;
    public int menit;
    public int jumlahKursi;

    public Penerbangan(int id, String pesawat, String asal, String tujuan, double harga, int hari, int bulan, int tahun, int jam, int menit, int jumlahKursi) {
        this.id = id;
        this.pesawat = pesawat;
        this.asal = asal;
        this.tujuan = tujuan;
        this.harga = harga;
        this.hari = hari;
        this.bulan = bulan;
        this.tahun = tahun;
        this.jam = jam;
        this.menit = menit;
        this.jumlahKursi = jumlahKursi;
    }

    /** Info penerbangan */
    @Override
    public String toString() {
        String namaBulan = Helper.getNamaBulan(this.bulan);
        DecimalFormat df = new DecimalFormat("#,###");
        String waktu = Helper.formatDuaDigit(jam) + ":" + Helper.formatDuaDigit(menit) + ":00";
        return "ID:" + id +
                " | " + pesawat +
                " | " + asal + " -> " + tujuan +
                " | " + Helper.formatDuaDigit(hari) + " " + namaBulan + " " + tahun + " " + waktu +
                " | Harga: Rp" + df.format(harga) +
                " | Kursi: " + jumlahKursi;
    }
}