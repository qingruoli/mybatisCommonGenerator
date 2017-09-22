/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.gznb.member.controller;

import com.github.pagehelper.PageInfo;
import com.gznb.member.entity.activity.AEActivityDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.gznb.member.service.ActivityService;

import java.util.List;

/**
 * @author liuzh
 * @since 2015-12-19 11:10
 */
@Controller
@RequestMapping("/activities")
public class CountryController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping
    public ModelAndView getAll(AEActivityDetail activityDetail) {
        ModelAndView result = new ModelAndView("index");
        PageInfo activityDetailPageInfo = activityService.getAll(activityDetail);
        result.addObject("pageInfo", activityDetailPageInfo);
        result.addObject("queryParam", activityDetail);
        result.addObject("page", activityDetailPageInfo.getPageNum());
        result.addObject("rows", activityDetailPageInfo.getPageSize());
        return result;
    }

   /* @RequestMapping(value = "/add")
    public ModelAndView add() {
        ModelAndView result = new ModelAndView("view");
        result.addObject("country", new Country());
        return result;
    }

    @RequestMapping(value = "/view/{id}")
    public ModelAndView view(@PathVariable Integer id) {
        ModelAndView result = new ModelAndView("view");
        Country country = countryService.getById(id);
        result.addObject("country", country);
        return result;
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id, RedirectAttributes ra) {
        ModelAndView result = new ModelAndView("redirect:/countries");
        countryService.deleteById(id);
        ra.addFlashAttribute("msg", "删除成功!");
        return result;
    }*/

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(AEActivityDetail activityDetail) {
        ModelAndView result = new ModelAndView("view");
        String msg = activityDetail.getId() == null ? "新增成功!" : "更新成功!";
        activityService.save(activityDetail);
        result.addObject("country", activityDetail);
        result.addObject("msg", msg);
        return result;
    }
}
