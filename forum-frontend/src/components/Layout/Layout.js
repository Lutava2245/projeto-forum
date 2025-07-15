import {Link, Outlet} from 'react-router-dom';

export default function Layout() {
    return (
        <div className="layout">
            <header className="header">
                <Link to="/" className="logo">Forum</Link>
                <Link to="/posts/create">Criar postagem</Link>
            </header>
            <main className="main-content">
                <Outlet />
            </main>
            <footer className="footer">
                &copy; 2025 Forum. All rights reserved.
            </footer>
        </div>
    );
}