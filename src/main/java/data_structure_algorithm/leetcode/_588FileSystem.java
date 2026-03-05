package data_structure_algorithm.leetcode;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class _588FileSystem {

    /**
     * 设计一个内存文件系统，支持下面四种操作。
     *
     * ls(String path)
     * 如果 path 指向一个文件，返回一个只包含该文件名的列表。
     * 如果 path 指向一个目录，返回该目录下所有文件和子目录的名字。
     * 返回结果必须按字典序排列。
     *
     * mkdir(String path)
     * 按给定路径创建一个新目录。
     * 题目保证这个目录路径此前不存在；如果路径中间的目录不存在，你需要把它们一并创建出来。
     *
     * addContentToFile(String filePath, String content)
     * 如果 filePath 不存在，创建该文件并写入 content。
     * 如果 filePath 已存在，把 content 追加到原内容后面。
     *
     * readContentFromFile(String filePath)
     * 返回 filePath 指向文件的内容。
     *
     * 约束
     * 1 <= path.length, filePath.length <= 100
     * path 和 filePath 都是绝对路径，以 / 开头，且不以 / 结尾——唯一例外是 path 本身就是根目录 "/"。
     * 所有目录名和文件名只含小写字母；同一个目录下不会有重名。
     * 所有操作的参数都合法：用户不会去读取/列出一个不存在的文件或目录。
     * 1 <= content.length <= 50
     * ls、mkdir、addContentToFile、readContentFromFile 总调用次数不超过 300。
     *
     *
     * 调用示例：
     * FileSystem fs = new FileSystem();
     * fs.ls("/");                         // 返回 []
     * fs.mkdir("/a/b/c");
     * fs.addContentToFile("/a/b/c/d", "hello");
     * fs.ls("/");                         // 返回 ["a"]
     * fs.readContentFromFile("/a/b/c/d"); // 返回 "hello"
     */

    public static class Solution1 {

        class FileSystem {

            // 每个节点既能当目录又能当文件
            private class Node {
                boolean isFile = false;            // 区分目录 / 文件
                StringBuilder content = new StringBuilder();  // 文件才用得到
                // 用 TreeMap：key（名字）天然字典序，ls 直接取用，不用再排
                TreeMap<String, Node> children = new TreeMap<>();
            }

            private final Node root = new Node();

            public List<String> ls(String path) {
                Node node = locate(path);
                // 指向文件：只返回文件名本身
                if (node.isFile) {
                    List<String> res = new ArrayList<>();
                    res.add(lastSegment(path));
                    return res;
                }
                // 指向目录：返回所有子项名字（TreeMap 已字典序）
                return new ArrayList<>(node.children.keySet());
            }

            public void mkdir(String path) {
                buildPath(path);   // 沿途缺失的中间目录一并建出来
            }

            public void addContentToFile(String filePath, String content) {
                Node node = buildPath(filePath);
                node.isFile = true;
                node.content.append(content);   // 不存在则建、已存在则追加
            }

            public String readContentFromFile(String filePath) {
                return locate(filePath).content.toString();
            }

            // 只走不建：用于 ls / read（题目保证路径一定存在）
            private Node locate(String path) {
                Node node = root;
                for (String seg : split(path)) {
                    node = node.children.get(seg);
                }
                return node;
            }

            // 边走边建：缺哪个节点就补哪个，返回末端节点。用于 mkdir / addContent
            private Node buildPath(String path) {
                Node node = root;
                for (String seg : split(path)) {
                    node.children.putIfAbsent(seg, new Node());
                    node = node.children.get(seg);
                }
                return node;
            }

            // 按 "/" 切段，过滤掉空串（开头的 "/"、根目录 "/" 都会切出空段）
            private List<String> split(String path) {
                List<String> segs = new ArrayList<>();
                for (String s : path.split("/")) {
                    if (!s.isEmpty()) segs.add(s);
                }
                return segs;
            }

            private String lastSegment(String path) {
                List<String> segs = split(path);
                return segs.get(segs.size() - 1);
            }

        }

    }



    public static class Solution2 {

        /**
         * LeetCode 588 - Design In-Memory File System, extended.
         *
         * Core idea: a trie whose edges are keyed by PATH SEGMENTS (names),
         * not single characters. Each node is either a directory (has children)
         * or a file (isFile = true, carries content).
         *
         * Beyond the original 4 ops, this adds the common interview follow-ups:
         *   - rm(path)        : delete a file or an entire directory subtree
         *   - mv(src, dest)   : move / rename a file or directory
         *   - path normalize  : "." (current), ".." (parent), "//" (empty) handled in split()
         *
         * A separate ConcurrentFileSystem below shows the thread-safety follow-up.
         */
        public class FileSystem {

            private class Node {
                boolean isFile = false;
                StringBuilder content = new StringBuilder();
                // TreeMap keeps child names in lexicographic order -> ls is sorted for free
                TreeMap<String, Node> children = new TreeMap<>();
            }

            private final Node root = new Node();

            // ---------- original four operations ----------

            public List<String> ls(String path) {
                Node node = locate(path);
                if (node.isFile) {
                    List<String> res = new ArrayList<>();
                    res.add(lastSegment(path));
                    return res;
                }
                return new ArrayList<>(node.children.keySet());
            }

            public void mkdir(String path) {
                buildPath(path);
            }

            public void addContentToFile(String filePath, String content) {
                Node node = buildPath(filePath);
                node.isFile = true;
                node.content.append(content);
            }

            public String readContentFromFile(String filePath) {
                return locate(filePath).content.toString();
            }

            // ---------- follow-up: delete ----------

            /** Remove a file or a whole directory subtree. No-op if path is "/" or missing. */
            public void rm(String path) {
                List<String> segs = split(path);
                if (segs.isEmpty()) return;                 // refuse to remove root
                Node parent = root;
                for (int i = 0; i < segs.size() - 1; i++) {
                    parent = parent.children.get(segs.get(i));
                    if (parent == null) return;             // path does not exist
                }
                parent.children.remove(segs.get(segs.size() - 1));
                // dropping the entry detaches the whole subtree; GC reclaims it
            }

            // ---------- follow-up: move / rename ----------

            /**
             * Move the node at src to dest (dest is the full target path, Unix `mv src dest`).
             * Works for files and directories. Creates missing parent dirs of dest.
             * Rejects moving a directory into itself or its own descendant.
             */
            public void mv(String src, String dest) {
                List<String> srcSegs = split(src);
                List<String> destSegs = split(dest);
                if (srcSegs.isEmpty() || destSegs.isEmpty()) return;
                if (isPrefix(srcSegs, destSegs)) return;    // can't move into self/descendant

                // detach src from its parent, keeping the node
                Node srcParent = root;
                for (int i = 0; i < srcSegs.size() - 1; i++) {
                    srcParent = srcParent.children.get(srcSegs.get(i));
                    if (srcParent == null) return;
                }
                Node node = srcParent.children.remove(srcSegs.get(srcSegs.size() - 1));
                if (node == null) return;                   // src does not exist

                // attach under dest's parent (building intermediate dirs if needed)
                Node destParent = root;
                for (int i = 0; i < destSegs.size() - 1; i++) {
                    destParent.children.putIfAbsent(destSegs.get(i), new Node());
                    destParent = destParent.children.get(destSegs.get(i));
                }
                destParent.children.put(destSegs.get(destSegs.size() - 1), node);
            }

            // ---------- helpers ----------

            /** Walk without creating. Used by read-only ops (ls / read). */
            private Node locate(String path) {
                Node node = root;
                for (String seg : split(path)) {
                    node = node.children.get(seg);
                }
                return node;
            }

            /** Walk and create missing nodes. Used by mkdir / addContentToFile. */
            private Node buildPath(String path) {
                Node node = root;
                for (String seg : split(path)) {
                    node.children.putIfAbsent(seg, new Node());
                    node = node.children.get(seg);
                }
                return node;
            }

            /**
             * Split + normalize: handles leading '/', root "/", "//" (empty segs),
             * "." (skip) and ".." (pop) using a stack. This is LeetCode 71 inside split().
             */
            private List<String> split(String path) {
                Deque<String> stack = new ArrayDeque<>();
                for (String s : path.split("/")) {
                    if (s.isEmpty() || s.equals(".")) continue;
                    if (s.equals("..")) {
                        if (!stack.isEmpty()) stack.pollLast();
                    } else {
                        stack.offerLast(s);
                    }
                }
                return new ArrayList<>(stack);
            }

            private String lastSegment(String path) {
                List<String> segs = split(path);
                return segs.get(segs.size() - 1);
            }

            private boolean isPrefix(List<String> a, List<String> b) {
                if (a.size() > b.size()) return false;
                for (int i = 0; i < a.size(); i++) {
                    if (!a.get(i).equals(b.get(i))) return false;
                }
                return true;
            }

        }

        /**
         * Thread-safe follow-up. Simplest correct approach: one ReentrantReadWriteLock
         * over the whole tree. Reads (ls/read) share the read lock; writes (mkdir/
         * addContent/rm/mv) take the exclusive write lock. Good for read-heavy loads.
         *
         * Finer granularity (per-node or per-path locks) scales better under contention
         * but must acquire locks in a consistent order (e.g. root-to-leaf) to avoid
         * deadlock -- mention this in the interview rather than implementing it blind.
         */
        class ConcurrentFileSystem {
            private final FileSystem fs = new FileSystem();
            private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
            private final Lock r = lock.readLock();
            private final Lock w = lock.writeLock();

            public List<String> ls(String p)                 { r.lock(); try { return fs.ls(p); }                 finally { r.unlock(); } }
            public String read(String p)                     { r.lock(); try { return fs.readContentFromFile(p); } finally { r.unlock(); } }
            public void mkdir(String p)                       { w.lock(); try { fs.mkdir(p); }                     finally { w.unlock(); } }
            public void addContentToFile(String p, String c) { w.lock(); try { fs.addContentToFile(p, c); }       finally { w.unlock(); } }
            public void rm(String p)                          { w.lock(); try { fs.rm(p); }                        finally { w.unlock(); } }
            public void mv(String s, String d)               { w.lock(); try { fs.mv(s, d); }                     finally { w.unlock(); } }
        }

    }
}
