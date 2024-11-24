interface WaterConservationSystem {
    int calculateTrappedWater(int[] blockHeights);
}

abstract class RainySeasonConservation implements WaterConservationSystem {
}

class CityBlockConservation extends RainySeasonConservation {
    @Override
    public int calculateTrappedWater(int[] blockHeights) {
        int totalTrappedWater = 0;
        
        for (int i = 0; i < blockHeights.length; i++) {
            int maxLeft = 0;
            int maxRight = 0;
            
            if (i > 0) {
                for (int j = i; j >= 0; j--) {
                    if (blockHeights[j] > maxLeft) {
                        maxLeft = blockHeights[j];
                    }
                }
            }

            for (int j = i; j < blockHeights.length; j++) {
                if (blockHeights[j] > maxRight) {
                    maxRight = blockHeights[j];
                }
            }

            // System.out.println("CURRENT ITEM: " + blockHeights[i] + " Max Left: " + maxLeft + " Max Right: " + maxRight);

            int maxCapacity = Math.min(maxLeft, maxRight);
            int storedWater = maxCapacity - blockHeights[i];
            // System.out.println("Stored Capacity" + ((storedWater > 0) ? storedWater : 0));

            totalTrappedWater += (storedWater > 0) ? storedWater : 0;
        }


        return totalTrappedWater;
    }
}

class lab5 {
    public static void main(String[] args) {
        int[] arr = {3, 0, 0, 2, 0, 4};
        WaterConservationSystem obj = new CityBlockConservation();
        System.out.println(obj.calculateTrappedWater(arr));

        int[] arr2 = {3, 0, 0, 2, 0, 4, 0, 0, 3, 0, 1, 0, 3};
        System.out.println(obj.calculateTrappedWater(arr2));
    }
}
