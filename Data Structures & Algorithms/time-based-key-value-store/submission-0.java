class TimeMap {

public static class Node{
        int timestamp;
        String value;

        public Node(String value,int timestamp){
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    Map<String,ArrayList<Node>> map = new HashMap<>();


    public TimeMap() {
        
    }
    
    public void set(String key, String value, int timestamp) {
        if(map.containsKey(key)){
            Node node = new Node(value,timestamp);
            map.get(key).add(node);
        }else{
            Node node = new Node(value,timestamp);
            ArrayList<Node> nodes = new ArrayList<>();
            nodes.add(node);
            map.put(key,nodes);
        }
        
    }
    
    public String get(String key, int timestamp) {
        //  If there are no values, it returns "".
        if(!map.containsKey(key)){
            return "";
        }
        // 开始去二分查找对应的这个最大的元素。
        ArrayList<Node> nodes = map.get(key);
        // 找到第一个，就是说 对应的timestamp<=timestamp && -1的，要求就是说 > timestamp的。
        // [start,end]
        int start = 0;
        int end = nodes.size()-1;

        // 也有可能就只有一个元素？这个时候start==end了？
        while(start<=end){
            int midIndex = (start+end)/2;
            // 判断mid是不是？
            if(nodes.get(midIndex).timestamp <= timestamp && ( midIndex==nodes.size()-1 || nodes.get(midIndex+1).timestamp > timestamp ) ){
                return nodes.get(midIndex).value;
            }else{
                // 开始更新。
                if(nodes.get(midIndex).timestamp > timestamp){
                    // 一定在左侧。
                    end = midIndex - 1;
                }else{
                    // 一定在右侧。
                    start = midIndex + 1;
                }
            }
        }
        // 没能找到。
        return "";
    }
}
