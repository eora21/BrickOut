package type;

import coordinate.Vector;

public interface Movable extends Runnable {

    /**
     * 움직일 수 있음을 설정할 수 있다.
     */
    public void start();

    /**
     * 움직일 수 없음을 설정할 수 있다.
     */
    public void stop();

    /**
     * next 호출시 변화할 변화량 설정이 가능하다
     *
     * @param motion
     */
    public void setMotion(Vector motion);

    public Vector getMotion();

    /**
     * 단위 시간당 추가되는 외부 효과. 이동에 영향을 준다.
     * 예를 들어, 중력, 바람 등.
     *
     * @param effect
     */
    public void addEffect(Vector effect);

    /**
     * MovableWorld에서는 일정 시간 간격으로 호출하며, 호출시 설정된 motion 변화량만큼 이동하면 된다.
     */
    public void next();
}
