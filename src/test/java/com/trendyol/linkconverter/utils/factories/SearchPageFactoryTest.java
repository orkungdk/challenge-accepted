package com.trendyol.linkconverter.utils.factories;

import com.trendyol.linkconverter.models.SearchPageDTO;
import com.trendyol.linkconverter.utils.LinkUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author orkungedik
 */
public class SearchPageFactoryTest {

    @Test
    public void testGenerate() {
        var uri = LinkUtils.generateURI("ty://?Page=Search&Query=phones");
        var result = SearchPageFactory.getInstance().generate(uri);

        Assert.assertTrue(result instanceof SearchPageDTO);
    }


}
