package com.example.anujdawar.bus_service_1;
import java.util.LinkedList;

public class graphAPI
{
        int V;
        int E;
        LinkedList<Integer>[] adj;
        boolean marked[];
        int edgeTo[];

        graphAPI(int v)
        {
            this.V = v;
            this.E = 0;

            this.adj = new LinkedList[V];

            for(int i = 0; i < V; i++)
                this.adj[i] = new LinkedList<>();

            this.marked = new boolean[V];
            this.edgeTo = new int[V];
        }

    public void addEdge(int a, int b)
    {
        this.adj[a].add(b);
        this.E++;
    }

    public Iterable<Integer> adj(int a)
    {
        return this.adj[a];
    }

    public boolean hasPathTo(int a)
    {
        return this.marked[a];
    }

    public void dfs(int v)
    {
        this.marked[v] = true;

        for(int i : adj(v))
            if(!marked[i])
            {
                edgeTo[i] = v;
                dfs(i);
            }
    }

    public void printGraph()
    {
        for(int i = 0; i < this.V; i++)
            for(int j : adj(i))
                System.out.println(i + "-" + j);
    }

    public Iterable<Integer> pathTo(int a, int b)
    {
        if(!hasPathTo(b))
            return null;

        LinkedList<Integer> path = new LinkedList<Integer>();

        for(int x = b; x != a; x = edgeTo[x])
            path.push(x);

        path.push(a);
        return path;
    }
}
