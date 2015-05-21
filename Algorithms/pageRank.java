/**
 * Created by xylollipop on 2015/4/13.
 * Copyleft
 * 此程序假设有4个节点，0 1表示是否可达，是有向图。
 *
 */
public class pageRank {
    //node数组是横向到纵列是否可达 后面get数组是纵列到横向的概率  即 1/出度
   // public static int[][] node={{0,1,1,1},{1,0,0,1},{1,0,0,0},{0,1,1,0}};
    public static int[][] node={{0,1,1,1},{0,0,0,0},{0,0,0,1},{0,1,0,1}};
    public static double a = 0.85;
    public double[] p;
    public pageRank(){
        p = new double[]{0.25,0.25,0.25,0.25};
    }
    public void display(double[][] a){
        int i,j;
        for(i=0;i<a.length;i++){
            for(j=0;j<a[0].length-1;j++){
                System.out.print(a[i][j]+"          ");
            }
            System.out.println(a[i][j]);
        }
    }
    public void ranking(int count){
        double[][] temp=new double[4][4];
        int s;
        boolean[] judge = new boolean[4];
        for(int i=0;i<node.length;i++){
            s = 0;
            for(int j=0;j<node[0].length;j++){
                if(node[i][j]!=0){
                    s++;
                    judge[j] = true;
                }
                else
                    judge[j] = false;
            }
            for(int k=0;k<node[0].length;k++){
                if(judge[k])
                    temp[k][i] = (double)1/s;
                else
                    temp[k][i] = 0;
            }
        }
        int m,n;
        while(count!=0){
            double[] newP = {0,0,0,0};
            for(m=0;m<temp.length;m++){
                for(n=0;n<temp[0].length;n++)
                    newP[m]+=p[n]*temp[m][n]*a;
                newP[m]+=(1-a)*0.25;    //0.25即1/4  因为一共有4个节点 这是防止dead ends和陷阱强加上的的纯数学写法
            }
            for(int k=0;k<p.length;k++){
                p[k] = newP[k];
                System.out.printf("%13.9f", p[k]);
            }
            System.out.print("\n");
            count--;
        }

    }

    public static void main(String[] args){
        pageRank rk = new pageRank();
        rk.ranking(10);
    }

}
