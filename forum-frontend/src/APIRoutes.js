import { BrowserRouter, Routes, Route } from "react-router-dom";

import Layout from "./components/Layout/Layout";
import Home from "./components/Home/Home";
import PostList from "./components/posts/PostList/PostList";
import PostDetail from "./components/posts/PostDetail/PostDetail";
import PostCreateForm from "./components/posts/PostCreateForm/PostCreateForm";
import PostEditForm from "./components/posts/PostEditForm/PostEditForm";
import PostDelete from "./components/posts/PostDelete/PostDelete";
import NotFound from "./components/NotFound/NotFound";

export default function APIRoutes() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<Home />} />
                    <Route path="posts" element={<PostList />} />
                    <Route path="posts/:id" element={<PostDetail />} />
                    <Route path="posts/edit/:id" element={<PostEditForm />} />
                    <Route path="posts/create" element={<PostCreateForm />} />
                    <Route path="posts/delete/:id" element={<PostDelete />} />
                    <Route path="*" element={<NotFound />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}