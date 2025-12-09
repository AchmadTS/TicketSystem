package controller;

public class HapusPenerbangan {
    private SistemTiket sistem;

    public HapusPenerbangan(SistemTiket sistem) {
        this.sistem = sistem;
    }

    public void run() {
        sistem.view.showDaftarPenerbangan(sistem.daftarPenerbangan, sistem.jumlahPenerbangan);

        if (sistem.jumlahPenerbangan == 0) {
            System.out.println("\n⚠️ Belum ada penerbangan yang bisa dihapus.");
            return;
        }

        System.out.println();

        int id = 0;
        boolean idValid = false;
        while (!idValid) {
            System.out.print("Masukkan ID penerbangan yang akan dihapus: ");
            String inputId = sistem.input.nextLine().trim();

            if (inputId.isEmpty()) {
                System.out.println("❌ ID tidak boleh kosong!");
                continue;
            }

            boolean formatValid = true;
            for (int i = 0; i < inputId.length(); i++) {
                char c = inputId.charAt(i);
                if (c < '0' || c > '9') {
                    formatValid = false;
                    break;
                }
            }

            if (!formatValid) {
                System.out.println("❌ ID harus berupa angka!");
                continue;
            }

            id = Integer.parseInt(inputId);
            idValid = true;
        }

        int idx = -1;

        for (int i = 0; i < sistem.jumlahPenerbangan; i++) {
            if (sistem.daftarPenerbangan[i].id == id) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            System.out.println("❌ Penerbangan dengan ID tersebut tidak ditemukan.");
            return;
        }

        System.out.println("\n📋 Data yang akan dihapus:");
        System.out.println(sistem.daftarPenerbangan[idx]);
        System.out.println();

        System.out.print("⚠️  Yakin ingin menghapus penerbangan ini? (y/n): ");
        String konfirmasi = sistem.input.nextLine().trim().toLowerCase();

        if (!konfirmasi.equals("y")) {
            System.out.println("❌ Penghapusan dibatalkan.");
            return;
        }

        for (int i = idx; i < sistem.jumlahPenerbangan - 1; i++) {
            sistem.daftarPenerbangan[i] = sistem.daftarPenerbangan[i + 1];
        }

        sistem.jumlahPenerbangan--;
        System.out.println("✅ Penerbangan berhasil dihapus!");
    }
}
