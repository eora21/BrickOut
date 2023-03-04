package type;

import event.CollisionEventListener;
import matter.Matter;

public interface Bouncible extends Movable {
//    /**
//     * 허용 공간 설정. 미설정시 제약 없음.
//     *
//     * @param x
//     * @param y
//     * @param width
//     * @param height
//     */
//    public void setBounds(int x, int y, int width, int height);
//
//    /**
//     * 제약 공간 등록. 제약 공간은 하나이상 구성 가능
//     *
//     * @param matter
//     */
//
//    public void addExcludedBounds(Matter matter);

    /**
     * 충돌(허용 공간을 벗어나거나, 제약 공간에 들어간 감) 감지 이벤트 수신자 설정.
     * @param listener
     */
    public void addCollisionEventListener(CollisionEventListener listener);
}
