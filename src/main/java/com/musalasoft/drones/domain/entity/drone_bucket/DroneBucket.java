package com.musalasoft.drones.domain.entity.drone_bucket;

import com.musalasoft.drones.domain.entity.drone.Drone;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DroneBucket {
    private Drone drone;
    private double totalWeight;
    private final List<DroneBucketItem> droneBucketItems = new ArrayList<>();

    public DroneBucket() {
    }

    public DroneBucket(Drone drone) {
        setDrone(drone);
    }

    public DroneBucket(Drone drone, List<DroneBucketItem> droneBucketItems) {
        setDrone(drone);
        addItemsToDroneBucket(droneBucketItems);
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = DroneBucketValidation.validateDrone(drone);
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    private void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public List<DroneBucketItem> getDroneBucketItems() {
        return droneBucketItems;
    }

    /**
     * Adds items to drone bucket item list.
     * This will add new items to the bucket and increase the total weight accordingly.
     * <p>
     * This is not a replacement of items. To replace items in the bucket with a new item list, first clear out the items
     * using clearDroneBucketItems() method and then add items through this method.
     * </p>
     * <p>
     * If there are null items in the list, they will be ignored without an error.
     * </p>
     *
     * @param droneBucketItems Drone bucket items to be added.
     * @throws NullPointerException If the passed list is null.
     */
    public void addItemsToDroneBucket(final List<DroneBucketItem> droneBucketItems) throws NullPointerException {
        final List<DroneBucketItem> nonNullItemList = Objects.requireNonNull(droneBucketItems).stream()
                .filter(Objects::nonNull).toList();

        this.droneBucketItems.addAll(nonNullItemList);
        setTotalWeight(getTotalWeight() + calculateDroneBucketItemsWeight(nonNullItemList));
    }

    /**
     * Adds an item to drone bucket item list.
     * This will add the new item to the bucket and increase the total weight accordingly.
     *
     * @param droneBucketItem Drone bucket item to be added.
     * @throws NullPointerException If the passed item is null.
     */
    public void addItemToDroneBucket(final DroneBucketItem droneBucketItem) throws NullPointerException {
        final DroneBucketItem nonNullDroneBucketItem = Objects.requireNonNull(droneBucketItem);

        this.droneBucketItems.add(nonNullDroneBucketItem);
        setTotalWeight(getTotalWeight() + nonNullDroneBucketItem.getWeight());
    }

    public static double calculateDroneBucketItemsWeight(final List<DroneBucketItem> droneBucketItems) {
        return droneBucketItems.stream().mapToDouble(DroneBucketItem::getWeight).sum();
    }

    /**
     * Clears drone bucket item list.
     * Sets total weight to 0.0 grams.
     */
    public void clearDroneBucketItems() {
        this.droneBucketItems.clear();
        setTotalWeight(0.0);
    }
}
