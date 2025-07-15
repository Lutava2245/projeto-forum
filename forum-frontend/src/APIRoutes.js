import { BrowserRouter, Routes, Route } from "react-router-dom";

import Layout from "./components/Layout/Layout";
import Home from "./components/Home/Home";
import PostList from "./components/PostList/PostList";
import PostDetail from "./components/PostDetail/PostDetail";
import NotFound from "./components/NotFound/NotFound";

export default function APIRoutes() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<Home />} />
                    <Route path="posts" element={<PostList />} />
                    <Route path="posts/:id" element={<PostDetail />} />
                    <Route path="*" element={<NotFound />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}