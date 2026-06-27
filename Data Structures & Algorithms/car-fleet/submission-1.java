class Solution {
    public static class Car{
        int position;
        int speed;
    }


    public int carFleet(int target, int[] position, int[] speed) {
        if(position.length==0){
            return 0;
        }
        if(position.length==1){
            return 1;
        }

        ArrayList<Car> cars = new ArrayList<>();
        for(int i = 0;i<position.length;i++){
            Car car = new Car();
            car.position = position[i];
            car.speed = speed[i];
            cars.add(car);
        }
        // OK，逆序去排序。
        Collections.sort(cars, (a,b)->{ return b.position - a.position;});
        int fleetTopIndex = 0;
        int ans = 1;
        int fleetCount = 1;
        for(int i = 1; i< cars.size();i++){
            // 获取当前的元素，比对我们的fleetTop的元素。
            if(reachable(cars.get(fleetTopIndex),cars.get(i),fleetCount,target)){
                fleetCount++;
            }else{
                fleetTopIndex = i;
                ans++;
                fleetCount = 1;
                // 注意，下一次从哪里开始？
                // 似乎就是从i+1开始，没什么大的问题？
            }
        }
        return ans;
    }


    // 前面的fleet的数量，注意，不包含current。
    // 默认肯定就是从1开始。
    public boolean reachable(Car fleetTop,Car current,int fleetCount,int target){
        // fleetTop到了target的时候。
        // 我们看下，我们自己的current，有没有到这个target-fleetCount呢？
        // 到了，那就说明形成了fleet。没到，OK不是，他自己成为fleet。

        // 一个简单的数学问题，并且是线性的。

       if( (target - fleetTop.position) * current.speed>= (target  - current.position) * fleetTop.speed ){
            return true;
        }else{
            return false;
        }
    }
}
