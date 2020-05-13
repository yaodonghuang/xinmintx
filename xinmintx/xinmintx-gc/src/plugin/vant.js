import Vue from "vue";
import "vant/lib/index.css";
import { Button } from "vant";
import { Tabbar, TabbarItem } from "vant";
import { NavBar } from "vant";
import { Swipe, SwipeItem } from "vant";
import { Lazyload } from "vant";

Vue.use(Lazyload);
Vue.use(Swipe).use(SwipeItem);
Vue.use(Button);
Vue.use(NavBar);
Vue.use(Tabbar).use(TabbarItem);

import { Grid, GridItem } from "vant";
Vue.use(Grid).use(GridItem);

import { Loading } from "vant";
Vue.use(Loading);

import { PullRefresh } from "vant";
Vue.use(PullRefresh);

import { Toast } from "vant";
Vue.use(Toast);

import { Card } from "vant";
Vue.use(Card);

import { Cell, CellGroup } from "vant";
Vue.use(Cell).use(CellGroup);

import { Field } from "vant";
Vue.use(Field);

import { List } from "vant";
Vue.use(List);

import { Tab, Tabs } from "vant";
Vue.use(Tab).use(Tabs);

import { Image } from "vant";
Vue.use(Image);

import { ImagePreview } from "vant";
Vue.use(ImagePreview);
Vue.prototype.$imagePreview = ImagePreview;

import { Stepper } from "vant";
Vue.use(Stepper);

import { SubmitBar } from "vant";
Vue.use(SubmitBar);

import { Search } from "vant";
Vue.use(Search);

import { CouponCell, CouponList } from "vant";
Vue.use(CouponCell).use(CouponList);
import { Popup } from "vant";
Vue.use(Popup);

