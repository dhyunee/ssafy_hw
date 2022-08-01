
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		List<List<String>>slist=new ArrayList<>();
		int n=Integer.parseInt(br.readLine());
		int[] sw=new int [n];
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			sw[i]=Integer.parseInt(st.nextToken());
		}
		
		int sn=Integer.parseInt(br.readLine());
		for(int i=0;i<sn;i++) {
			StringTokenizer st1=new StringTokenizer(br.readLine());
			int g=Integer.parseInt(st1.nextToken());
			int num=Integer.parseInt(st1.nextToken());
			
			if(g==1) {
				for(int j=0;j<n;j++) {
					if((j+1)%num==0) {
						if(sw[j]==1)sw[j]=0;
						else sw[j]=1;
					}
				}
			}
		
			else if(g==2) {
				sw[num-1]=sw[num-1]==0?1:0;
				for(int j=1;j<num;j++) {
					if(num-1+j<n) {
						if(sw[num-1+j]==sw[num-1-j]) {
							sw[num-1-j]=sw[num-1-j]==0?1:0;
							sw[num-1+j]=sw[num-1-j];
						}
						else break;
					}
					else break; 
				}
			}
		}
		int cnt=0;
		for(int x:sw) {
			if(cnt==20) {
				cnt=0;
				System.out.println();
			}
			System.out.print(x+" ");
			cnt++;
		}
	}

}
