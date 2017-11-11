package com.sampler;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;
import com.sampler.utils.GdxUtils;

/**
 * Created by Marc Holman on 5/09/2016.
 */
public class AshleyEngineSample extends SampleBase {

    private static final Logger log = new Logger(AshleyEngineSample.class.getName(), Logger.DEBUG);

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(AshleyEngineSample.class);

    private static final float SPAWN_TIME = 1f;
    private static final float REMOVE_TIME = 3f;

    private Engine engine;

    private Array<Entity> bullets = new Array<Entity>();

    private float spawnTimer;
    private float removeTimer;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        engine = new Engine();
        engine.addEntityListener(new EntityListener() {
            @Override
            public void entityAdded(Entity entity) {
                log.debug("entityAdded= " + entity);
                log.debug("total entities= " + engine.getEntities().size());
            }

            @Override
            public void entityRemoved(Entity entity) {
                log.debug("entityRemoved= " + entity);
                log.debug("total entities= " + engine.getEntities().size());
            }
        });

        addBullet();
    }

    private void addBullet() {
        Entity bullet = new Entity();
        bullets.add(bullet);
        engine.addEntity(bullet);
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        float delta = Gdx.graphics.getDeltaTime();
        engine.update(delta);

        spawnTimer += delta;

        if (spawnTimer > SPAWN_TIME) {
            spawnTimer = 0;
            addBullet();
        }

        removeTimer += delta;

        if (removeTimer > REMOVE_TIME) {
            removeTimer = 0;

            if (bullets.size > 0) {
                Entity bullet = bullets.first();
                bullets.removeValue(bullet, true);
                engine.removeEntity(bullet);
            }
        }
    }


}
