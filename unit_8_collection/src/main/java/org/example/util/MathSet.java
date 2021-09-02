package org.example.util;

public class MathSet<Numbers extends Number & Comparable<Numbers>> {

    private final int DEFAULT_SIZE = 10;
    private int capacity;
    private Numbers[] array;

    public MathSet() {
        array = (Numbers[]) new Number[DEFAULT_SIZE];
    }

    public MathSet(int size) {
        array = (Numbers[]) new Number[size];
    }

    public int size() {
        return capacity;
    }

    public MathSet(Numbers[] numbers) {
        array = (Numbers[]) new Number[numbers.length];
        for (Numbers number : numbers) {
            add(number);
        }
    }

    public MathSet(Numbers[]... numbers) {
        int size = 0;
        for (Numbers[] number : numbers) {
            size += number.length;
        }
        array = (Numbers[]) new Number[size];
        for (Numbers[] number : numbers) {
            for (Numbers t : number) {
                add(t);
            }
        }

    }

    public MathSet(MathSet numbers) {
        array = (Numbers[]) new Number[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            add((Numbers) numbers.get(i));
        }
    }

    public MathSet(MathSet<Numbers>... numbers) {
        int size = 0;
        for (MathSet<Numbers> n : numbers) {
            size += n.size();
        }
        array = (Numbers[]) new Number[size];
        for (MathSet<Numbers> n : numbers) {
            for (int i = 0; i < n.size(); i++) {
                add((Numbers) n.get(i));
            }
        }
    }

    public void add(Numbers o) {
        if (!contains(o)) {
            if (capacity == array.length) {
                array = copyOf(array, array.length + 5);
            }
            array[capacity] = o;
            capacity++;
        }
    }

    public void add(Numbers... o) {
        for (int i = 0; i < o.length; i++) {
            add(o[i]);
        }
    }

    public void join(MathSet ms) {
        Numbers[] setValues = (Numbers[]) ms.toArray();
        this.add(setValues);
    }

    public void join(MathSet... ms) {
        for (MathSet mathSet : ms) {
            this.join(mathSet);
        }
    }

    public void intersection(MathSet ms) {
        MathSet<Numbers> resulted = new MathSet<>();
        for (int i = 0; i < this.capacity; i++) {
            for (int j = 0; j < ms.capacity; j++) {
                if (this.array[i] == ms.array[j]) {
                    resulted.add((Numbers) ms.array[j]);
                }
            }
        }
        this.clear();
        this.join(new MathSet(resulted));
    }

    public void intersection(MathSet... ms) {
        for (MathSet mathSet : ms) {
            this.intersection(mathSet);
        }
    }

    public MathSet cut(int firstIndex, int lastIndex) {
        MathSet<Numbers> cutted = new MathSet<>();
        for (int i = 0; i < capacity; i++) {
            if (i >= firstIndex && i <= lastIndex) cutted.add(array[i]);
        }
        return cutted;
    }

    public Numbers get(int index) {
        if (index < 0 || index > capacity) throw new IllegalArgumentException("Incorrect index" + index);
        return array[index];
    }

    public Numbers getMin() {
        Numbers min = array[0];
        for (int i = 1; i < capacity; i++) {
            if (array[i].compareTo(min) < 0) min = array[i];
        }
        return min;
    }

    public Numbers getMax() {
        Numbers max = array[0];
        for (int i = 1; i < capacity; i++) {
            if (array[i].compareTo(max) > 0) max = array[i];
        }
        return max;
    }

    public Numbers[] toArray() {
        return array;
    }

    public Numbers[] toArray(int firstIndex, int lastIndex) {
        Numbers[] returnedArray = (Numbers[]) new Number[firstIndex - lastIndex + 1];
        if (returnedArray.length >= 0) System.arraycopy(array, firstIndex, returnedArray, 0, returnedArray.length);
        return returnedArray;
    }

    public boolean remove(Numbers o) {
        if (capacity != 0) {
            boolean isWasInList = false;
            int indexObject = 0;
            for (int i = 0; i < capacity; i++) {
                if (array[i].equals(o)) {
                    isWasInList = true;
                    indexObject = i;
                    break;
                }
            }
            if (isWasInList) {
                boolean switcher = false;
                Numbers[] changed = (Numbers[]) (new Number[capacity - 1]);
                for (int i = 0; i < capacity - 1; i++) {
                    if (i == indexObject) {
                        switcher = true;
                    }
                    if (switcher)
                        changed[i] = array[i + 1];
                    else
                        changed[i] = array[i];
                }
                array = changed;
                capacity--;
                return !contains(o);
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            array[i] = null;
        }
        capacity = 0;
    }

    public void clear(int firstIndex, int lastIndex) {
        swap(firstIndex, lastIndex);
        if (lastIndex > capacity)
            lastIndex = capacity;
        for (int i = firstIndex; i < lastIndex; i++) {
            array[i] = null;
        }
    }

    public void clear(Numbers[] cleared) {
        int counter = 0;
        for (int i = 0; i < cleared.length; i++) {
            if (contains(cleared[i])) remove(cleared[i]);
        }
        capacity = 0;
    }

    public Number getAverage() {
        Double sum = 0d;
        for (int i = 0; i < capacity; i++) {
            sum += array[i].doubleValue();
        }
        return sum / capacity;
    }

    public Number getMedian() {
        if (capacity != 0) {
            sortAsc();
            if (capacity % 2 != 0) return array[capacity / 2];
            else {
                Double first = array[capacity / 2].doubleValue();
                Double second = array[capacity / 2 - 1].doubleValue();
                return (first + second) / 2;
            }
        } else return null;
    }

    public void sortDesc() {
        sortDesc(0, capacity);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        MathSet bufferTo = this.cut(0, firstIndex - 1);
        MathSet buffer = this.cut(firstIndex, lastIndex);
        MathSet bufferFrom = this.cut(lastIndex + 1, this.capacity);
        buffer.sort(false);
        bufferTo.join(new MathSet(buffer));
        bufferTo.join(new MathSet(bufferFrom));
        this.array = (Numbers[]) bufferTo.array;
    }

    public void sortDesc(Numbers value) {
        int index = value.intValue();
        sortDesc(0, index - 1);
    }

    public void sortAsc() {
        sortAsc(0, array.length);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        MathSet bufferTo = this.cut(0, firstIndex - 1);
        MathSet buffer = this.cut(firstIndex, lastIndex);
        MathSet bufferFrom = this.cut(lastIndex + 1, this.capacity);
        buffer.sort(true);
        bufferTo.join(new MathSet(buffer));
        bufferTo.join(new MathSet(bufferFrom));
        this.array = (Numbers[]) bufferTo.array;
    }

    public void sortAsc(Numbers value) {
        int index = value.intValue();
        sortAsc(0, index - 1);
    }

    private void sort(boolean ascending) {
        for (int i = capacity - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (ascending && !isBigger(i, j)) swap(i, j);
                else if (!ascending && isBigger(i, j)) swap(i, j);
            }
        }
    }

    private boolean isBigger(int firstIndex, int secondIndex) {
        return array[firstIndex].compareTo(array[secondIndex]) > 0;
    }

    private void swap(int firstIndex, int secondIndex) {
        Numbers b = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = b;
    }

    private boolean contains(Numbers o) {
        boolean contained = false;
        for (int i = 0; i < capacity; i++) {
            if (o.equals(array[i])) {
                contained = true;
                break;
            }
        }
        return contained;
    }

    private Numbers[] copyOf(Numbers[] array, int changedSize) {
        int copiedSize = 0;
        if (changedSize < array.length) copiedSize = changedSize;
        else copiedSize = array.length;

        Numbers[] copied = (Numbers[]) (new Number[changedSize]);
        for (int i = 0; i < copiedSize; i++) {
            copied[i] = array[i];
        }
        return copied;
    }
}
