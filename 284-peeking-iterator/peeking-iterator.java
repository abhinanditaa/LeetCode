class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> iterator;
    private Integer nextValue;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;

        if (iterator.hasNext()) {
            nextValue = iterator.next();
        }
    }

    public Integer peek() {
        return nextValue;
    }

    @Override
    public Integer next() {
        Integer result = nextValue;

        if (iterator.hasNext()) {
            nextValue = iterator.next();
        } else {
            nextValue = null;
        }

        return result;
    }

    @Override
    public boolean hasNext() {
        return nextValue != null;
    }
}