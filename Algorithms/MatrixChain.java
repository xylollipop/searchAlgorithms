/**
 *矩阵链
 *
 */
public class MatrixChain {
    static final int M = 1000000;
    public int[][] s;
    public int[] p;


    public static void main(String[] args){
        int[] p1 = {3,5,2,1,10};
        int[] p2 = {2,7,3,6,10};
        int[] p3 = {10, 3, 15, 12, 7, 2};
        int[] p4 = {7, 2, 4, 15, 20, 5};
        MatrixChain a = new MatrixChain(p1);
        MatrixChain b = new MatrixChain(p2);
        MatrixChain c = new MatrixChain(p3);
        MatrixChain d = new MatrixChain(p4);
        a.MatrixChainOrder();
        b.MatrixChainOrder();
        c.MatrixChainOrder();
        d.MatrixChainOrder();
        //a.PrintOptimal(1, 4);


    }

    public MatrixChain(int[] p){
        this.p = p;
        s = new int[p.length][p.length];
    }


    public void MatrixChainOrder(){
        int n = p.length-1;
        int temp;
        int[][] m = new int [n+1][n+1];
        //int[][] s = new int [n+1][n+1];
        for(int i=1;i<=n;i++)
            m[i][i] = 0;
        for(int l=2;l<=n;l++){
            for(int i=1;i<=n-l+1;i++){
                int j = i+l-1;
                m[i][j] = M;
                for(int k=i;k<=j-1;k++){
                    temp = m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j];
                    if(temp<m[i][j]){
                        m[i][j] = temp;
                        s[i][j] = k;
                    }
                }
            }
        }
        PrintOptimal(1,p.length-1);

    }



    public void PrintOptimal(int i,int j){
        if(i==j)
            System.out.print("A"+i);
        else{
            System.out.print("(");
            PrintOptimal(i,s[i][j]);
            PrintOptimal(s[i][j]+1,j);
            System.out.print(")");
        }
    }






}
