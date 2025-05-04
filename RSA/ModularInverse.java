package RSA;

public class ModularInverse {
    // Brute-force
    // a * a^-1 mod m == 1 

    public int find_inverse(int a, int m){
        for(int inv = 0; inv<m; inv++){
            if((a*inv)%m == 1){
                return inv;
            }
        }
        return -1;
    }
}
