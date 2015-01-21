package ar.com.dgarcia.vortex;

import app.ar.com.dgarcia.processing.sandbox.iterables.Collections;
import app.ar.com.dgarcia.processing.sandbox.iterables.MergeResult;
import ar.com.dgarcia.colecciones.sets.Sets;
import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import com.google.common.collect.Lists;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ikari on 21/01/2015.
 */
@RunWith(JavaSpecRunner.class)
public class MergeTest extends JavaSpec<TestContext> {
    @Override
    public void define() {
        describe("a merge result", () -> {

            it("can be created from two collections",()->{
                MergeResult<Integer> mergeResult = Collections.merge(Lists.newArrayList(1, 2, 3), Lists.newArrayList(1, 2, 3));
                assertThat(mergeResult).isNotNull();
            });

            describe("algorithm", () -> {

                it("extracts as added elements only present in the newer collection",()->{
                    ArrayList<Integer> older = Lists.newArrayList(1, 2, 3, 4);
                    ArrayList<Integer> newer = Lists.newArrayList(3, 4, 5, 6);

                    MergeResult<Integer> mergeResult = Collections.merge(older,newer);

                    assertThat(mergeResult.getAdded()).isEqualTo(Sets.newLinkedHashSet(5, 6));
                });
                it("extracts as removed elements only present in the older collection",()->{
                    ArrayList<Integer> older = Lists.newArrayList(1, 2, 3, 4);
                    ArrayList<Integer> newer = Lists.newArrayList(3, 4, 5, 6);

                    MergeResult<Integer> mergeResult = Collections.merge(older,newer);

                    assertThat(mergeResult.getRemoved()).isEqualTo(Sets.newLinkedHashSet(1, 2));
                });
                it("extracts as kept elements present in both collection",()->{
                    ArrayList<Integer> older = Lists.newArrayList(1, 2, 3, 4);
                    ArrayList<Integer> newer = Lists.newArrayList(3, 4, 5, 6);

                    MergeResult<Integer> mergeResult = Collections.merge(older,newer);

                    assertThat(mergeResult.getKept()).isEqualTo(Sets.newLinkedHashSet(3, 4));
                });
                
                it("extracts all newer as added if older is empty",()->{
                    ArrayList<Integer> older = Lists.newArrayList();
                    ArrayList<Integer> newer = Lists.newArrayList(3, 4, 5, 6);

                    MergeResult<Integer> mergeResult = Collections.merge(older,newer);

                    assertThat(mergeResult.getAdded()).isEqualTo(Sets.newLinkedHashSet(3, 4, 5, 6));
                }); 
                
                it("extracts all older as removed if newer is empty",()->{
                    ArrayList<Integer> older = Lists.newArrayList(1, 2, 3, 4);
                    ArrayList<Integer> newer = Lists.newArrayList();

                    MergeResult<Integer> mergeResult = Collections.merge(older,newer);

                    assertThat(mergeResult.getRemoved()).isEqualTo(Sets.newLinkedHashSet(1, 2, 3, 4));
                });   
                
                it("extracts all as kept is collection are equals",()->{
                    ArrayList<Integer> older = Lists.newArrayList(1, 2, 3, 4);
                    ArrayList<Integer> newer = Lists.newArrayList(1, 2, 3, 4);

                    MergeResult<Integer> mergeResult = Collections.merge(older,newer);

                    assertThat(mergeResult.getKept()).isEqualTo(Sets.newLinkedHashSet(1, 2, 3, 4));
                }); 
                
                it("extracts all as kept if collection contains same elements disregarding order",()->{
                    ArrayList<Integer> older = Lists.newArrayList(1, 2, 3, 4);
                    ArrayList<Integer> newer = Lists.newArrayList(3, 2, 4, 1);

                    MergeResult<Integer> mergeResult = Collections.merge(older,newer);

                    assertThat(mergeResult.getKept()).isEqualTo(Sets.newLinkedHashSet(1, 2, 3, 4));
                });   

            });
        });

    }
}