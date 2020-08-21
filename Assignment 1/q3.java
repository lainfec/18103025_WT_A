import java.util.*;

public class q3{
        static public void main(String args[]){
            Scanner s=new Scanner(System.in);
            System.out.print("Enter the number of vertices:\n");
            int v=s.nextInt();
            System.out.print("Enter the number of edges:\n");
            int e=s.nextInt();
            ArrayList<ArrayList<Integer>> adj_list=new ArrayList<ArrayList<Integer>>(v);
            for(int i=0;i<v;i++){
                adj_list.add(new ArrayList<Integer>());
            }
            System.out.print("Enter the Edges in the following format: Source Destination\n");
            for(int i=0;i<e;i++){
                int src=s.nextInt()-1;
                int dest=s.nextInt()-1;
                adj_list.get(src).add(dest);
                adj_list.get(dest).add(src);
            }
            System.out.print("Enter the source vortex:\n");
            int src=s.nextInt()-1;
            System.out.print("Enter the destination vortex:\n");
            int dest=s.nextInt()-1;
            s.close();
            sssp(adj_list,src,dest,v);

        }
        static private void sssp(ArrayList<ArrayList<Integer>> adj,int s,int d,int v){
            int parent[]=new int[v];
            int distance[]=new int[v];

            if(BFS(adj,s,d,v,parent,distance)==false){
                System.out.print("Negative Cycles exist");
                return;
            }

            String path="";
            int last_node=d;
            path+=(last_node+1)+" ";
            while(parent[last_node]!=-1){
                path+=(parent[last_node]+1)+" ";
                last_node=parent[last_node];
            }
            StringBuilder true_path = new StringBuilder(); 
            true_path.append(path);
            System.out.print("Path is:"+true_path.reverse());
        }
        static private boolean BFS(ArrayList<ArrayList<Integer>> adj,int s,int d, int v, int parent[], int distance[]){
            Queue<Integer> q=new LinkedList<>();
            boolean visited[]=new boolean[v];
            for(int i=0;i<v;i++){
                visited[i]=false;
                distance[i]=Integer.MAX_VALUE;
                parent[i]=-1;
            }
            visited[s]=true;
            distance[s]=0;
            q.add(s);
            while(!q.isEmpty()){
                int cur_vortex=q.remove();
                for(int i=0;i<adj.get(cur_vortex).size();i++){
                    if(visited[adj.get(cur_vortex).get(i)]==false){
                        visited[adj.get(cur_vortex).get(i)]=true;
                        distance[adj.get(cur_vortex).get(i)]=distance[cur_vortex]+1;
                        parent[adj.get(cur_vortex).get(i)]=cur_vortex;
                        q.add(adj.get(cur_vortex).get(i));
                        if(adj.get(cur_vortex).get(i)==d)
                        return true;
                    }
                }
            }
            return false;
        }
}